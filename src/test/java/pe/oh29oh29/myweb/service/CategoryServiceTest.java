package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CategoryDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Category;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoryServiceTest {
	
	@Autowired CategoryDao categoryDao;
	@Autowired CategoryService categoryService;
	@Autowired PostDao postDao;

	@Before
	public void setUp() throws Exception {
		postDao.deleteAllPosts();
		categoryDao.deleteAllCategories();
	}

	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 추가 (최상위)
	 */
	@Test
	public void addCategory() {
		// 카테고리 추가
		Category category = new Category();
		category.setName("카테고리이름");
		categoryService.addCategory(category);
		
		// 검증
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		Category category2 = categories.get(0);
		assertEquals("카테고리이름", category2.getName());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 추가 (하위) 
	 */
	@Test
	public void addCategory2() {
		// 최상위 카테고리 추가
		Category parentCategory = new Category();
		parentCategory.setName("카테고리최상위이름");
		categoryService.addCategory(parentCategory);
		
		// 검증
		List<Category> parentCategories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, parentCategories.size());
		Category parentCategory2 = parentCategories.get(0);
		assertEquals("카테고리최상위이름", parentCategory2.getName());
		
		// 하위 카테고리 추가
		Category childCategory = new Category();
		childCategory.setParentIdx(parentCategory2.getIdx());
		childCategory.setName("카테고리하위이름");
		categoryService.addCategory(childCategory);
		
		// 검증
		List<Category> childCategories = categoryService.findCategoriesByParentIdx(parentCategory2.getIdx());
		assertEquals(1, childCategories.size());
		Category childCategory2 = childCategories.get(0);
		assertEquals("카테고리하위이름", childCategory2.getName());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 추가 (유효하지 않는 상위 카테고리일 경우)
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void addCategoryByInvalidParentCategory() {
		// 하위 카테고리 추가
		Category childCategory = new Category();
		childCategory.setParentIdx(Utils.generateIdx());
		childCategory.setName("카테고리하위이름");
		categoryService.addCategory(childCategory);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 수정
	 */
	@Test
	public void modifyCategory() {
		// 카테고리 추가
		addCategory();
		
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		
		// 카테고리 수정
		Category category = categories.get(0);
		category.setIdx(category.getIdx());
		category.setName("카테고리이름수정");
		categoryService.modifyCategory(category);
		
		// 검증
		List<Category> categories2 = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories2.size());
		Category category2 = categories2.get(0);
		assertEquals("카테고리이름수정", category2.getName());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 삭제
	 */
	@Test
	public void removeCategoryByIdx() {
		// 카테고리 추가
		addCategory();
		
		List<Category> categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		
		// 카테고리 삭제
		Category category = categories.get(0);
		categoryService.removeCategoryByIdx(category.getIdx());
		
		// 검증
		List<Category> categories2 = categoryService.findCategoriesByParentIdx(null);;
		assertEquals(0, categories2.size());
		
	}

}
