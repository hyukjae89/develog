package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.dao.CategoryDao;
import pe.oh29oh29.myweb.model.Category;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoryServiceTest {
	
	@Autowired CategoryDao categoryDao;
	@Autowired CategoryService categoryService;

	@Before
	public void setUp() throws Exception {
		categoryDao.deleteAllCategories();
	}

	@Test
	public void addCategory() {
		Category category = new Category();
		category.setName("카테고리이름");
		categoryService.addCategory(category);
		
		List<Category> categoies = categoryService.findCategoriesByParentIdx(null);
		
		assertEquals(1, categoies.size());
		assertEquals("카테고리이름", categoies.get(0).getName());
	}
	
	@Test
	public void modifyCategory() {
		addCategory();
		List<Category> categories = null;
		categories = categoryService.findCategoriesByParentIdx(null);
		Category category = new Category();
		category.setIdx(categories.get(0).getIdx());
		category.setName("수정이름");
		
		categoryService.modifyCategory(category);
		categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		assertEquals("수정이름", categories.get(0).getName());
	}
	
	@Test
	public void removeCategoryByIdx() {
		addCategory();
		List<Category> categories = null;
		categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		
		categoryService.removeCategoryByIdx(categories.get(0).getIdx());
		categories = categoryService.findCategoriesByParentIdx(null);
		assertEquals(0, categories.size());
		
	}

}
