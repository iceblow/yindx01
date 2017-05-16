<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="./js/jquery-1.11.3.min.js"></script>
<div class="sidebar sidebar-fixed" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<ul class="nav nav-list">
		<li id="yonghu"><a data-href="#" class="dropdown-toggle"> <i
				class="icon-user"></i> <span class="menu-text">用户管理</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./user/appUser?page=1"
					data-href="./user/appUser?page=1"> <i
						class="icon-double-angle-right"></i> 前端用户管理
				</a></li>
				<li><a href="#./user/companyUser?page=1"
					data-href="./user/companyUser?page=1"> <i
						class="icon-double-angle-right"></i> 企业用户管理
				</a></li>
				<li><a href="#./user/auntUser?page=1"
					data-href="./user/auntUser?page=1"> <i
						class="icon-double-angle-right"></i> 阿姨用户管理
				</a></li>
			</ul></li>
		<li id="dingdan"><a data-href="#" class="dropdown-toggle"> <i
				class="icon-home"></i> <span class="menu-text">订单管理</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./order/todoOrderList?page=1"
					data-href="./order/todoOrderList?page=1"> <i
						class="icon-double-angle-right"></i> 待处理订单
				</a></li>
				<li><a href="#./order/completeOrderList?page=1"
					data-href="./order/completeOrderList?page=1"> <i
						class="icon-double-angle-right"></i> 完成订单
				</a></li>
				<li><a href="#./order/exceptionOrderList?page=1"
					data-href="./order/exceptionOrderList?page=1"> <i
						class="icon-double-angle-right"></i> 异常订单
				</a></li>
			</ul></li>

		<li id="shouye"><a data-href="#" class="dropdown-toggle"> <i
				class="icon-home"></i> <span class="menu-text">首页管理</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./index/appBannerList?page=1"
					data-href="./index/appBannerList?page=1"> <i
						class="icon-double-angle-right"></i> app首页轮播图管理
				</a></li>
				<li><a href="#./index/auntBannerList?page=1"
					data-href="./index/auntBannerList?page=1"> <i
						class="icon-double-angle-right"></i> 阿姨端轮播图表管理
				</a></li>
				<!--  	<li><a href="#./index/appADList?page=1"
					data-href="./index/appADList?page=1"> <i
						class="icon-double-angle-right"></i> app首页图片广告管理
				</a></li>
				-->
				<li><a href="#./home/goiconlist?page=1"
					data-href="./home/goiconlist?page=1"> <i
						class="icon-double-angle-right"></i> 首页家政分类配置
				</a></li>
				<li><a href="#./home/gocontentlist?page=1"
					data-href="./home/gocontentlist?page=1"> <i
						class="icon-double-angle-right"></i> 首页公司阿姨推荐
				</a></li>
				<li><a href="#./home/bannerManage?page=1"
					data-href="./home/bannerManage?page=1"> <i
						class="icon-double-angle-right"></i> 首页图片广告配置
				</a></li>

			</ul></li>


		<li id="shangjia"><a data-href="#" class="dropdown-toggle"> <i
				class="icon-home"></i> <span class="menu-text">系统配置</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./system/serviceCategory?page=1"
					data-href="./system/serviceCategory?page=1"> <i
						class="icon-double-angle-right"></i> 一级分类管理
				</a></li>
				<li><a href="#./system/serviceTwoCategory?page=1"
					data-href="./system/serviceTwoCategory?page=1"> <i
						class="icon-double-angle-right"></i> 三级分类管理
				</a></li>
				<li><a href="#./system/servicePrice?page=1"
					data-href="./system/servicePrice?page=1"> <i
						class="icon-double-angle-right"></i> 服务价格管理
				</a></li>
				<li><a href="#./system/serviceArea?page=1"
					data-href="./system/serviceArea?page=1"> <i
						class="icon-double-angle-right"></i> 城市服务管理
				</a></li>
			</ul></li>
		<li id="guanliyuan"><a data-href="#" class="dropdown-toggle">
				<i class="icon-tasks"></i> <span class="menu-text">管理员管理</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./admin/adminList?page=1"
					data-href="./admin/adminList?page=1"> <i
						class="icon-double-angle-right"></i> 管理员管理
				</a></li>
				<li><a href="#./admin/roleList?page=1"
					data-href="./admin/roleList?page=1"> <i
						class="icon-double-angle-right"></i> 角色管理
				</a></li>
				<li><a href="#./admin/PermissionList?page=1"
					data-href="./admin/PermissionList?page=1"> <i
						class="icon-double-angle-right"></i> 权限管理
				</a></li>
			</ul></li>
		<li id="jiesuan"><a class="dropdown-toggle"> <i
				class="icon-tasks"></i> <span class="menu-text">结算</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./aunt/cashlist" data-href="./aunt/cashlist">
						<i class="icon-double-angle-right"></i>提现记录
				</a></li>
				<li><a href="#./statistic/statistic"
					data-href="./statistic/statistic"> <i
						class="icon-double-angle-right"></i> 统计信息
				</a></li>
			</ul></li>
		<li id="qita"><a class="dropdown-toggle"> <i
				class="icon-tasks"></i> <span class="menu-text">其他</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./others/advices?page=1"
					data-href="./others/advices?page=1"> <i
						class="icon-double-angle-right"></i>意见反馈
				</a></li>
				<li><a href="#./others/qiandao?page=1"
					data-href="./others/qiandao?page=1"> <i
						class="icon-double-angle-right"></i>签到配置
				</a></li>
				<li><a href="#./others/banben" data-href="./others/banben">
						<i class="icon-double-angle-right"></i>版本更新
				</a></li>
				<li><a href="#./others/levelset" data-href="./others/levelset">
						<i class="icon-double-angle-right"></i>积分等级
				</a></li>
				<li><a href="#./others/baseconfig"
					data-href="./others/baseconfig"> <i
						class="icon-double-angle-right"></i>基本配置
				</a></li>
				<li><a href="#./others/ratio" data-href="./others/ratio"> <i
						class="icon-double-angle-right"></i>佣金抽成
				</a></li>
			</ul></li>
		<li id="youhuiquan"><a class="dropdown-toggle"> <i
				class="icon-tasks"></i> <span class="menu-text">优惠券</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./coupon/couponlist?page=1"
					data-href="./coupon/couponlist?page=1"> <i
						class="icon-double-angle-right"></i> 优惠券列表
				</a></li>
			</ul></li>
		<li id="push"><a class="dropdown-toggle"> <i
				class="icon-tasks"></i> <span class="menu-text">推送管理</span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="#./puse/puseList?page=1"
					data-href="./puse/puseList?page=1"> <i
						class="icon-double-angle-right"></i> 用户端推送
				</a></li>
				<li><a href="#./puse/puseAuntList?page=1"
					data-href="./puse/puseAuntList?page=1"> <i
						class="icon-double-angle-right"></i> 阿姨端推送
				</a></li>

			</ul></li>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>