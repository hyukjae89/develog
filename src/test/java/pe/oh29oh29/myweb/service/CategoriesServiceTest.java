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

import pe.oh29oh29.myweb.dao.CategoriesDao;
import pe.oh29oh29.myweb.model.Categories;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoriesServiceTest {
	
	@Autowired CategoriesDao categoriesDao;
	@Autowired CategoriesService categoriesService;

	@Before
	public void setUp() throws Exception {
		categoriesDao.deleteAllCategories();
	}

	@Test
	public void addCategory() {
		Categories category = new Categories();
		category.setName("카테고리이름");
		categoriesService.addCategory(category);
		
		List<Categories> categories = categoriesService.findCategoriesByParentIdx(null);
		
		assertEquals(1, categories.size());
		assertEquals("카테고리이름", categories.get(0).getName());
	}
	
	@Test
	public void modifyCategory() {
		addCategory();
		List<Categories> categories = null;
		categories = categoriesService.findCategoriesByParentIdx(null);
		Categories category = new Categories();
		category.setIdx(categories.get(0).getIdx());
		category.setName("수정이름");
		
		categoriesService.modifyCategory(category);
		categories = categoriesService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		assertEquals("수정이름", categories.get(0).getName());
	}
	
	@Test
	public void removeCategoryByIdx() {
		addCategory();
		List<Categories> categories = null;
		categories = categoriesService.findCategoriesByParentIdx(null);
		assertEquals(1, categories.size());
		
		categoriesService.removeCategoryByIdx(categories.get(0).getIdx());
		categories = categoriesService.findCategoriesByParentIdx(null);
		assertEquals(0, categories.size());
		
	}

}
