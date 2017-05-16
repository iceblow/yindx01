package com.uncleserver.controller.company;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.controller.BaseController;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.service.company.StatisticService;

@Controller
@RequestMapping("/company/statistic")
public class StatisticController extends BaseController {

	@Resource
	private StatisticService statisticService;

	@RequestMapping("/statistic")
	public ModelAndView statistic(HttpSession session, String startDate, String endDate) {
		ModelAndView mav = new ModelAndView();
		Integer companyid = (Integer) session.getAttribute("companyId");
		long times = statisticService.getCompanyAuntOrderCount(companyid, startDate, endDate);
		mav.addObject("times", times);

		CompanyExtra extra = statisticService.getExtraByCompanyId(companyid);
		if (extra != null) {
			String balance = extra.getBalance().toString();
			String use_total = extra.getUseTotal().toString();
			mav.addObject("balance", balance);
			mav.addObject("use_total", use_total);
		}

		Float totalprice = statisticService.getCompanyTotalPrice(companyid, startDate, endDate);
		if (totalprice == null) {
			mav.addObject("totalprice", "0");
		} else {
			mav.addObject("totalprice", totalprice.toString());
		}

		Float totalcash = statisticService.getTotalCashByCompany(companyid, startDate, endDate);
		if (totalcash == null) {
			mav.addObject("totalcash", "0");
		} else {
			mav.addObject("totalcash", totalcash.toString());
		}

		Float hours = statisticService.getTotalTimeByCompany(companyid, startDate, endDate, "1");
		if (hours == null) {
			mav.addObject("hours", "0");
		} else {
			mav.addObject("hours", hours.toString());
		}

		Float days = statisticService.getTotalTimeByCompany(companyid, startDate, endDate, "2");
		if (days == null) {
			mav.addObject("days", "0");
		} else {
			mav.addObject("days", days.toString());
		}
		return mav;
	}
}
