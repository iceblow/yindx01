package com.uncleserver.service.manage.Impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.Area;
import com.uncleserver.model.Category;
import com.uncleserver.model.CategoryCity;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.SerPrice;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.SetPriceResult;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageSystemService;
@Service("manageSystemService")
public class ManageSystemServiceImpl extends BaseServiceImpl implements ManageSystemService{

	@Override
	public List<Category> selectOneCategory() {
		List<Category> categories = categoryMapper.selectAllCategoryTo();
		return categories;
	}

	@Override
	public List<CategorySecond> selectTwoCategory() {
		List<CategorySecond> categorySeconds = categorySecondMapper.selectAllCategorySecond();
		return categorySeconds;
	}

	@Override
	public QueryResult<Category> getServiceCategory(PQuery pquery) {
		List<Category> user = categoryMapper.selectCategory(pquery.getStartPage(), pquery.getRows());
		long count = categoryMapper.selectCategoryCount();
		QueryResult<Category> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public Category getDetail(int id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public List<CategorySecond> selectByCategoryId(int id) {
		List<CategorySecond> categorySeconds = categorySecondMapper.selectByFid(id);
		for (CategorySecond c : categorySeconds) {
			c.setIcoString(getFilePathById(c.getIconid()));
		}
		return categorySeconds;
	}

	@Override
	public void updateCategory(int dataid, int id) {
		CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(id);
		if(categorySecond != null){
			categorySecond.setFid(dataid);
			categorySecondMapper.updateByPrimaryKey(categorySecond);
		}
	}

	@Override
	public void deleteCategory(int categoryid) {
		categoryMapper.deleteByPrimaryKey(categoryid);
	}

	@Override
	public List<Category> selectByName(String name) {
		List<Category> category = categoryMapper.selectByName(name);
		return category;
	}

	@Override
	public void addCategory(String name) {
		Category category = new Category();
		category.setAddtime(new Date());
		category.setName(name);
		category.setSort(99);
		categoryMapper.insert(category);
	}

	@Override
	public QueryResult<CategoryThird> getServiceTwoCategory(PQuery pquery, String category, String threeCategory) {
		List<CategoryThird> user = categoryThirdMapper.getServiceTwoCategory(pquery.getStartPage(), pquery.getRows(),category,threeCategory);
		long count = categoryThirdMapper.getServiceTwoCategoryCount(category,threeCategory);
		QueryResult<CategoryThird> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public List<CategoryThird> selectByCategoryIds(String category) {
		List<CategoryThird> categoryThirds = categoryThirdMapper.selectByCategoryIds(category);
		return categoryThirds;
	}

	@Override
	public List<CategoryThird> selectByCategoryIdTwo(int categoryid) {
		List<CategoryThird> categorySeconds = categoryThirdMapper.selectByFid(categoryid);
		return categorySeconds;
	}

	@Override
	public void deleteCategoryTwo(int categoryid) {
		categoryThirdMapper.deleteByPrimaryKey(categoryid);
		
	}

	@Override
	public List<CategoryThird> selectByNameTwo(String name, String categoryNone,String threeCategoryTwo) {
		List<CategoryThird> categoryThirds = categoryThirdMapper.selectByNameTwo(name,categoryNone,threeCategoryTwo);
		return categoryThirds;
	}

	@Override
	public void addCategoryTwo(String name, String categoryNone,String threeCategoryTwo) {
		CategoryThird categoryThird = new CategoryThird();
		if(!CommonUtils.isEmptyString(categoryNone)){
			categoryThird.setFid(CommonUtils.parseInt(categoryNone, 0));
		}else{
			categoryThird.setFid(0);
		}
		categoryThird.setAddtime(new Date());
		categoryThird.setName(name);
		categoryThird.setCategoryid(CommonUtils.parseInt(threeCategoryTwo, 0));
		categoryThirdMapper.insert(categoryThird);
	}

	@Override
	public List<Cities> selectCities() {
		List<Cities> cities = citiesMapper.selectAll();
		return cities;
	}

	@Override
	public QueryResult<SetPriceResult> getServicePrice(PQuery pquery, String city) {
		List<SetPriceResult> one = serPriceMapper.getServicePrice(pquery.getStartPage(), pquery.getRows());
		List<SetPriceResult> two = serPriceMapper.getServicePriceOther(city);
		long count = serPriceMapper.getServicePriceCount();
		for (SetPriceResult o : one) {
			for (SetPriceResult t : two) {
				if(o.getCategoryid() == t.getCategoryid()){
					if(o.getThird_categoryid() != null && t.getThird_categoryid() != null){
						if(o.getThird_categoryid() == t.getThird_categoryid() && t.getThird_categoryid() != 0){
							o.setDeposit_price(t.getDeposit_price());
							o.setPrice(t.getPrice());
							o.setPrice_small(t.getPrice_small());
						}else if(t.getThird_categoryid() == 0){
							o.setDeposit_price(t.getDeposit_price());
							o.setPrice(t.getPrice());
							o.setPrice_small(t.getPrice_small());
						}
					}
				}
			}
		}
		QueryResult<SetPriceResult> result = new QueryResult<>(one, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public void saveOrUpdate(int categoryid, int third_categoryid, String price, String deposit_price, String price_small,String city) {
		if(third_categoryid > 0){
			SerPrice serPrice = serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid, third_categoryid,city);
			if(serPrice != null){
				if(!CommonUtils.isEmptyString(price)){
					serPrice.setPrice(new BigDecimal(price));
				}
				if(!CommonUtils.isEmptyString(deposit_price)){
					serPrice.setDepositPrice(new BigDecimal(deposit_price));
				}
				if(!CommonUtils.isEmptyString(price_small)){
					serPrice.setPriceSmall(new BigDecimal(price_small));
				}
				serPriceMapper.updateByPrimaryKey(serPrice);
			}else{
				SerPrice serPrice2 = new SerPrice();
				if(!CommonUtils.isEmptyString(price)){
					serPrice2.setPrice(new BigDecimal(price));
				}
				if(!CommonUtils.isEmptyString(deposit_price)){
					serPrice2.setDepositPrice(new BigDecimal(deposit_price));
				}
				if(!CommonUtils.isEmptyString(price_small)){
					serPrice2.setPriceSmall(new BigDecimal(price_small));
				}
				serPrice2.setCategoryid(categoryid);
				serPrice2.setThirdCategoryid(third_categoryid);
				serPrice2.setAddtime(new Date());
				serPrice2.setCity(city);
				serPriceMapper.insert(serPrice2);
			}
		}else{
			SerPrice serPrice = serPriceMapper.selectByCategoryIdAndCity(categoryid,city);
			if(serPrice != null){
				if(!CommonUtils.isEmptyString(price)){
					serPrice.setPrice(new BigDecimal(price));
				}
				if(!CommonUtils.isEmptyString(deposit_price)){
					serPrice.setDepositPrice(new BigDecimal(deposit_price));
				}
				if(!CommonUtils.isEmptyString(price_small)){
					serPrice.setPriceSmall(new BigDecimal(price_small));
				}
				serPriceMapper.updateByPrimaryKey(serPrice);
			}else{
				SerPrice serPrice2 = new SerPrice();
				if(!CommonUtils.isEmptyString(price)){
					serPrice2.setPrice(new BigDecimal(price));
				}
				if(!CommonUtils.isEmptyString(deposit_price)){
					serPrice2.setDepositPrice(new BigDecimal(deposit_price));
				}
				if(!CommonUtils.isEmptyString(price_small)){
					serPrice2.setPriceSmall(new BigDecimal(price_small));
				}
				serPrice2.setCategoryid(categoryid);
				serPrice2.setThirdCategoryid(0);
				serPrice2.setAddtime(new Date());
				serPrice2.setCity(city);
				serPriceMapper.insert(serPrice2);
			}
		}
	}

	@Override
	public void updateCat(String name, byte is,String id) {
		CategorySecond category = categorySecondMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		if(category != null){
			category.setName(name);
			category.setNeedpic(is);
			categorySecondMapper.updateByPrimaryKey(category);
		}
	}

	@Override
	public List<Proviences> selectproviences() {
		List<Proviences> cities = proviencesMapper.selectAll();
		return cities;
	}

	@Override
	public List<Cities> selectCity(int id) {
		List<Cities> list = citiesMapper.selectAll();
		return list;
	}

	@Override
	public QueryResult<CategoryCity> getServiceArea(PQuery pquery, String city) {
		List<CategoryCity> user = categoryCityMapper.getServiceArea(pquery.getStartPage(), pquery.getRows(),city);
		long count = categoryCityMapper.getServiceAreaCount(city);
		List<CategorySecond> list = categorySecondMapper.selectAllCategorySecond();
		for (CategoryCity categoryCity : user) {
			for (CategorySecond categorySecond : list) {
				if(categoryCity.getDataid() == categorySecond.getDataid()){
					categoryCity.setName(categorySecond.getName());
				}
			}
		}
		QueryResult<CategoryCity> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public List<Area> selectArea(int id) {
		List<Area> list = areaMapper.selectByCityid(id);
		return list;
	}

	@Override
	public List<CategorySecond> getCategorySecond() {
		List<CategorySecond> list = categorySecondMapper.selectAllCategorySecond();
		return list;
	}

	@Override
	public void addArea(String city, String area, String provience, String second) {
		CategoryCity categoryCity = new CategoryCity();
		categoryCity.setAddtime(new Date());
		categoryCity.setArea(area);
		categoryCity.setCity(city);
		categoryCity.setCategoryid(CommonUtils.parseInt(second, 0));
		categoryCityMapper.insert(categoryCity);
	}

	@Override
	public void delArea(String id) {
		categoryCityMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
	}
}
