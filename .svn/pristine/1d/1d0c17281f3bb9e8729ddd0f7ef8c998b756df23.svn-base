package com.uncleserver.controller.manage;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.controller.BaseController;
import com.uncleserver.service.company.StatisticService;

@Controller
@RequestMapping("/manage/statistic")
public class ManageStatisticController extends BaseController {

	@Resource
	private StatisticService statisticService;

	@RequestMapping("/statistic")
	public ModelAndView statistic(HttpSession session, String startDate, String endDate) {
		ModelAndView mav = new ModelAndView();
		long times = statisticService.getCompanyAuntOrderCount(null, startDate, endDate);
		mav.addObject("times", times);

		BigDecimal balance = statisticService.getAllCompanyBalance();
		if (balance == null) {
			mav.addObject("balance", 0);
		} else {
			mav.addObject("balance", balance);
		}
		BigDecimal use_total = statisticService.getAllCompanyUseTotal();
		if (use_total == null) {
			mav.addObject("use_total", 0);
		} else {
			mav.addObject("use_total", use_total);
		}

		Float totalprice = statisticService.getCompanyTotalPrice(null, startDate, endDate);
		if (totalprice == null) {
			mav.addObject("totalprice", "0");
		} else {
			mav.addObject("totalprice", totalprice.toString());
		}

		Float totalcash = statisticService.getTotalCashByCompany(null, startDate, endDate);
		if (totalcash == null) {
			mav.addObject("totalcash", "0");
		} else {
			mav.addObject("totalcash", totalcash.toString());
		}

		Float hours = statisticService.getTotalTimeByCompany(null, startDate, endDate, "1");
		if (hours == null) {
			mav.addObject("hours", "0");
		} else {
			mav.addObject("hours", hours.toString());
		}

		Float days = statisticService.getTotalTimeByCompany(null, startDate, endDate, "2");
		if (days == null) {
			mav.addObject("days", "0");
		} else {
			mav.addObject("days", days.toString());
		}
		return mav;
	}
}
