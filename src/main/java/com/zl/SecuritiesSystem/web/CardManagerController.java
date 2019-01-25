package com.zl.SecuritiesSystem.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.SecuritiesSystem.dto.BankCardExecution;
import com.zl.SecuritiesSystem.dto.UserAccountExecution;
import com.zl.SecuritiesSystem.dto.UserBankCardExecution;
import com.zl.SecuritiesSystem.dto.UserCentersExecution;
import com.zl.SecuritiesSystem.entity.BankCard;
import com.zl.SecuritiesSystem.entity.UserCenters;
import com.zl.SecuritiesSystem.enums.CardStateEnum;
import com.zl.SecuritiesSystem.enums.UserAccountEnum;
import com.zl.SecuritiesSystem.enums.UserBankCardStateEnum;
import com.zl.SecuritiesSystem.enums.UserCentersStateEnum;
import com.zl.SecuritiesSystem.exceptions.BankCardOperationException;
import com.zl.SecuritiesSystem.service.BankCardService;
import com.zl.SecuritiesSystem.service.UserAccountService;
import com.zl.SecuritiesSystem.service.UserBankCardService;
import com.zl.SecuritiesSystem.service.UserCentersService;
import com.zl.SecuritiesSystem.util.HttpServletRequestUtil;
import com.zl.SecuritiesSystem.util.ModelMapUtil;
import com.zl.SecuritiesSystem.util.RedisUtil;
import com.zl.SecuritiesSystem.util.RestTest;


@Controller
@RequestMapping(value="card")
/**
 * 相关功能与
 * @author 王徐
 *
 */
public class CardManagerController {
	
	@Autowired
	private BankCardService bankCardService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserBankCardService userBankCardService;
	@Autowired
	private UserCentersService userCentersService;
	
	/**
	 * 获取该用户绑定的银行卡, 以及用户的账户信息
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2,  通过用户的id查询, 用户的用户的银行卡信息
	 * 3, 查询 该用户绑定的账户 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bankcardlist", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Map<String, Object> BankCardList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null) {
			try {
				Integer userId = userCenters.getUserId();
				BankCardExecution bankCardExecution = bankCardService.getBankCardListByUserId(userId);
				if (bankCardExecution.getState() == CardStateEnum.SUCCESS.getState()) {
					modelMap.put("bankCardList", bankCardExecution.getBankCardList());
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
				}
				//
				UserAccountExecution userAccountExecution = userAccountService.getUserAccountByUserId(userId);
				if (userAccountExecution.getState() == UserAccountEnum.SUCCESS.getState()) {
					modelMap.put("userAccount", userAccountExecution.getUserAccount());
					return ModelMapUtil.modelMapWhileTrue(modelMap);
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
				}
			} catch (BankCardOperationException e) {
				return ModelMapUtil.modelMapWhileCatchException(modelMap, e);
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		} 
	}
	
	
	/**
	 * 登录校验
	 * 判断用户是否已经登录了,登录了才能进行后面的操作,否则不能进行以后的操作并直接返回,
	 * @param request
	 * @return
	 */
	private UserCenters userIsLogin(HttpServletRequest request) {
		//这里需要从登录时设置的session中获取数据
		UserCenters userCenters = (UserCenters) request.getSession().getAttribute("user");		
		if (userCenters!=null) {
			return userCenters; 
		}
		//TODO
		//这里是由于没有整合,所以我们是无法获取user的session,因此在这里我们直接返回一个User对象,后面整合了,直接改成null即可
		UserCenters tempUserCenters = new UserCenters();
		tempUserCenters.setUserId(4);
		tempUserCenters.setUserLoginName("张三");
		return tempUserCenters;
	}

	/**
	 * 
	 * 此方法的主要功能是向用户的手机发送验证码,
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2. 判断用户传递过来的手机号是否为空, 为空就不进行发送短信操作, 不为空,就向用户发送短信
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkinfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		String phoneNum = HttpServletRequestUtil.getString(request, "phoneNum");
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null) {
			if (phoneNum != null && phoneNum!="") {
				try {
					System.err.println(phoneNum);
					String code = RestTest.sendPhone(phoneNum, "1");
					//设置前缀
					String prefix = UUID.randomUUID().toString();
					RedisUtil.setCachePrefix(prefix);
					//存储一个key ,value
					RedisUtil.setString(phoneNum, code,60);
					System.err.println(RedisUtil.getString(phoneNum));
					return ModelMapUtil.modelMapWhileTrue(modelMap);
				} catch (Exception e) {
					return ModelMapUtil.modelMapWhileCatchException(modelMap, e);
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "手机号码为空");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 用户添加银行卡, 
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2, 校验用户输入的验证码是否与redis中缓存的验证码是否一致, 不一致返回
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addcard", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCard(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null) {
			//获取前台传递过来的bankCard对象
			BankCard bankCard = getBankCard(request);
			BankCardExecution bankCardExecution = bankCardService.queryBankCardByBankCardNum(bankCard.getCardNum());
			if (bankCardExecution.getBankCard() != null) {
				boolean isUnion = cardIsUnion(bankCardExecution.getBankCard().getCardId());
				if (isUnion) {
					modelMap.put("success", true);
					modelMap.put("message", "该银行卡已经绑定过了,请确认");
					return modelMap;
				}
				boolean detailIsSame = checkDetailIsSame(bankCard, bankCardExecution.getBankCard(), userCenters.getUserLoginName());
				boolean isTrue =  checkInputNum(request);
				System.out.println("detailIsSame:" + detailIsSame +"  isTrue:" + isTrue);
				if (isTrue && detailIsSame) {
					userBankCardService.addUnion(userCenters.getUserId(),  bankCardExecution.getBankCard().getCardId());
					return ModelMapUtil.modelMapWhileTrue(modelMap);
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "输入的信息与当前用户不匹配!请重新输入正确的信息");
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "当前输入的银行卡不存在, 请输入正确的银行卡号!");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 判定该银行卡 是否已经绑定了
	 * 绑定了返回true
	 * 没有绑定则返回 FALSE
	 * @param integer
	 * @return
	 */
	private boolean cardIsUnion(Integer cardId) {
		Integer effectedNum = userBankCardService.cardIsExit(cardId);
		if (effectedNum > 0) {
			//提交的项目这时写错了
			return true;
		}
		return false;
	}


	/**
	 * 校验用户输入的数据,与其在银行中存在的银行卡数据是否一致,
	 * 且当前登录用户的姓名必须与其银行卡的姓名, 输入的姓名一致
	 * 一致返回true
	 * 不一致返回false
	 * @param bankCard
	 * @param bankCardInBank
	 * @param userLoginName
	 * @return
	 */
	private boolean checkDetailIsSame(BankCard bankCard, BankCard bankCardInBank, String userLoginName) {
		System.out.println(bankCard);
		System.out.println(bankCardInBank);
		System.out.println(userLoginName);
		if (
				bankCardInBank.getCardOwner().equals(bankCard.getCardOwner())
				&& bankCardInBank.getCardNum().equals(bankCard.getCardNum())
				&& bankCardInBank.getCardTel().equals(bankCard.getCardTel())
				&& bankCardInBank.getCardOwner().equals(userLoginName)
				&& bankCardInBank.getCardType().equals(bankCard.getCardType())
		) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取前台传递过来的bankcard对象
	 * 并
	 * @param request
	 * @return
	 */
	private BankCard getBankCard(HttpServletRequest request) {
		BankCard bankCard = new BankCard();
		bankCard.setCardBalance(new BigDecimal("0"));//初始化余额为零
		bankCard.setCardNum(HttpServletRequestUtil.getString(request, "cardNum"));
		bankCard.setCardOwner(HttpServletRequestUtil.getString(request, "cardOwnerName"));
		bankCard.setCardPwd("123456"); //初始化密码为123456
		bankCard.setCardTel(HttpServletRequestUtil.getString(request, "phoneNum"));
		bankCard.setCardType(HttpServletRequestUtil.getString(request, "selectBank"));
		return bankCard;
	}


	/**
	 * 对手机的验证码进行校验,redis中的存储的值,获取到的phone值,以及输入的校验码不一致,均返回false
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkInputNum(HttpServletRequest request) {
		String phoneNum = HttpServletRequestUtil.getString(request, "phoneNum");
		String checkNum = HttpServletRequestUtil.getString(request, "checkNum");
		String checkNumInPhone = RedisUtil.getString(phoneNum);
		System.out.println("phoneNum"+phoneNum+"   checkNum"+checkNum+"     checkNumInPhone"+checkNumInPhone);
		if (checkNumInPhone != null && phoneNum != null) {
			if (checkNum.equals(checkNumInPhone)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	
	/**
	 * 通过银行卡 的id将银行卡的信息,以及该银行卡的关联信息删除掉
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2.校验用户输入的验证码是否与redis中缓存的验证码是否一致, 不一致返回
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletecardunionbycardid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteCardByCardId(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null) {		
			boolean isTrue =  checkInputNum(request);
			if (isTrue) {
				Integer cardId = HttpServletRequestUtil.getInt(request, "cardId");
				if (cardId != null && cardId > 0) {
					//删除该银行卡与用户的关联信息
					UserBankCardExecution userBankCardExecution = userBankCardService.deleteUnion(cardId);
					if (userBankCardExecution.getState() == UserBankCardStateEnum.SUCCESS.getState()) {
						return ModelMapUtil.modelMapWhileTrue(modelMap);
					} else {
						return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
					}
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "当前银行卡的id为空,请核对信息");
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "验证码不匹配");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 通过银行卡 的id, 将该银行卡具体的信息查寻出来
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2, 判断前台传递过来的cardId是否为空, 为空则返回, 存在则进行下一步
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findcardbycardid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findCardByCardId(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null) {
			Integer cardId = HttpServletRequestUtil.getInt(request, "cardId");
			if (cardId != null && cardId > 0) {
				BankCardExecution bankCardExecution = bankCardService.getBankCardByCardId(cardId);
				if (bankCardExecution.getState() == CardStateEnum.SUCCESS.getState()) {
					modelMap.put("card", bankCardExecution.getBankCard());
					return ModelMapUtil.modelMapWhileTrue(modelMap);
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "当前银行卡的id为空,请核对信息");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 根据用户登录的id将用户的信息查询出来,这里我们只查询,手机号和密码
	 * 主要用户, 用户信息的回显, 
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/finduserinfobylogid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findUserDetail(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null && userCenters.getUserId() != null) {
			UserCentersExecution userCentersExecution =  getPhoneAndPwd(userCenters.getUserId());
			if (userCentersExecution.getState() == UserCentersStateEnum.SUCCESS.getState()) {
				modelMap.put("userInfo", userCentersExecution.getUserCenters());
				return ModelMapUtil.modelMapWhileTrue(modelMap);
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 获得用户的手机号和登录密码,便于校验, 
	 * @param userId
	 * @return
	 */
	public UserCentersExecution getPhoneAndPwd(Integer userId) {
		UserCentersExecution userCentersExecution = new UserCentersExecution();
		userCentersExecution = userCentersService.getUserInfo(userId);
		return userCentersExecution;
	}
	
	/**
	 * 用户的密码的更新操作, 
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2.校验用户输入的密码是否与数据库中的密码一致, 不一致返回, 一致则进行下一步
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateuserpwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserPwd(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null && userCenters.getUserId() != null) {
			boolean isSame = checkPwdsIsSame(request);
			if (isSame) {
				String newPwd = HttpServletRequestUtil.getString(request, "newPwd");
				UserCentersExecution userCentersExecution = userCentersService.updateUserPwd(userCenters.getUserId(),newPwd);
				if (userCentersExecution.getState() == UserCentersStateEnum.SUCCESS.getState()) {
					return ModelMapUtil.modelMapWhileTrue(modelMap);
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "旧密码与数据库中密码不匹配");
			}
		}else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}

	/**
	 * 判断后台传递过来的原来的密码是否与数据库中所存的原密码一致
	 * 一致返回true
	 * 不一致返回false
	 * @param request
	 * @return
	 */
	private boolean checkPwdsIsSame(HttpServletRequest request) {
		UserCentersExecution userCentersExecution = getPhoneAndPwd(userIsLogin(request).getUserId());
		String oldPwdInDataBase = userCentersExecution.getUserCenters().getUserPwd();
		String oldPwdFromBefore = HttpServletRequestUtil.getString(request, "oldPwd");
		if (oldPwdInDataBase.equals(oldPwdFromBefore)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 更新用户账户的信息, 
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2, 校验输入的手机验证码是否与服务器发送的验证码一致, 不一致就返回false
	 * 3, 校验前台传递过来的资金密码与交易密码是否为空,若为空就不进行修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateaccountpwd", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Map<String, Object> updateAccountPwd(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null && userCenters.getUserId() != null) {
			boolean isTrue =  checkInputNum(request);
			if (isTrue) {
				String dealPwd = HttpServletRequestUtil.getString(request, "dealPwd");
				String billPwd = HttpServletRequestUtil.getString(request, "billPwd");
				if (dealPwd != null && billPwd != null) {
					UserAccountExecution userAccountExecution = 
							userAccountService.updateDealPwdAndBillPwd(userCenters.getUserId(), dealPwd, billPwd);
					if (userAccountExecution.getState() == UserAccountEnum.SUCCESS.getState()) {
						return ModelMapUtil.modelMapWhileTrue(modelMap);
					}else {
						return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
					}
				} else {
					return ModelMapUtil.modelMapWhileWrong(modelMap, "资金密码或者交易密码不能为空!");
				}
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "验证码不匹配");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
	
	/**
	 * 查找用户的手机号, 
	 * 1, session中当前的用户, 为空,则说明没有登录, 返回. 存在则进行下一步
	 * 2, 通过登录用户的id进行查询手机号
	 * 3, 对查找的结果进行判断
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findphonenumofuser", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findPhoneNumOfUser(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		UserCenters userCenters = userIsLogin(request);
		if (userCenters != null && userCenters.getUserId() != null) {
			UserCentersExecution userCentersExecution = userCentersService.findPhoneNumOfUserByLogId(userCenters.getUserId());
			if (userCentersExecution.getState() == UserCentersStateEnum.SUCCESS.getState()) {
				modelMap.put("userInfo", userCentersExecution.getUserCenters());
				return ModelMapUtil.modelMapWhileTrue(modelMap);
			} else {
				return ModelMapUtil.modelMapWhileWrong(modelMap, "操作数据库数据失败!");
			}
		} else {
			return ModelMapUtil.modelMapWhileWrong(modelMap, "用户未登录,请先登录");
		}
	}
}
