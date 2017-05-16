package com.uncleserver.service.manage;

import java.util.List;

import com.uncleserver.model.Area;
import com.uncleserver.model.Category;
import com.uncleserver.model.CategoryCity;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.SetPriceResult;

public interface ManageSystemService {

	List<Category> selectOneCategory();

	List<CategorySecond> selectTwoCategory();

	QueryResult<Category> getServiceCategory(PQuery pquery);

	Category getDetail(int id);

	List<CategorySecond> selectByCategoryId(int id);

	void updateCategory(int dataid, int id);

	void deleteCategory(int categoryid);

	List<Category> selectByName(String name);

	void addCategory(String name);

	QueryResult<CategoryThird> getServiceTwoCategory(PQuery pquery, String category, String threeCategory);

	List<CategoryThird> selectByCategoryIds(String category);

	List<CategoryThird> selectByCategoryIdTwo(int categoryid);

	void deleteCategoryTwo(int categoryid);

	List<CategoryThird> selectByNameTwo(String name, String categoryNone, String threeCategoryTwo);

	void addCategoryTwo(String name, String categoryNone, String threeCategoryTwo);

	List<Cities> selectCities();

	QueryResult<SetPriceResult> getServicePrice(PQuery pquery, String city);

	void saveOrUpdate(int categoryid, int third_categoryid, String price, String deposit_price, String price_small,String city);

	void updateCat(String name, byte is, String id);

	List<Proviences> selectproviences();

	List<Cities> selectCity(int id);

	QueryResult<CategoryCity> getServiceArea(PQuery pquery, String city);

	List<Area> selectArea(int id);

	List<CategorySecond> getCategorySecond();

	void addArea(String city, String area, String provience, String second);

	void delArea(String id);

}
