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
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.dao.MemberDao;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.model.Category;
import pe.oh29oh29.myweb.model.Member;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.service.CategoryService.AccessSpecifier;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoryServiceTest {
	
	@Autowired CategoryDao categoryDao;
	@Autowired CategoryService categoryService;
	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	@Autowired CommentDao commentDao;
	@Autowired CommentService commentService;
	
	private TestHelper testHelper;
	
	@Before
	public void setUp() throws Exception {
		commentDao.deleteAllComments();
		postDao.deleteAllPosts();
		categoryDao.deleteAllCategories();
		memberDao.deleteAllMembers();
		
		testHelper = new TestHelper(memberService, categoryService, postService, commentService);
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
		List<Category> categories = categoryService.findCategories(null);
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
		List<Category> parentCategories = categoryService.findCategories(null);
		assertEquals(1, parentCategories.size());
		Category parentCategory2 = parentCategories.get(0);
		assertEquals(parentCategory.getName(), parentCategory2.getName());
		
		// 하위 카테고리 추가
		Category subCategory = new Category();
		subCategory.setParentIdx(parentCategory2.getIdx());
		subCategory.setName("카테고리하위이름");
		categoryService.addCategory(subCategory);
		
		// 검증
		List<Category> subCategories = categoryService.findCategories(parentCategory2.getIdx(), AccessSpecifier.TOTAL);
		assertEquals(1, subCategories.size());
		Category childCategory2 = subCategories.get(0);
		assertEquals(subCategory.getName(), childCategory2.getName());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 추가 (유효하지 않는 상위 카테고리일 경우)
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void addCategoryByInvalidParentCategory() {
		// 하위 카테고리 추가
		Category subCategory = new Category();
		subCategory.setParentIdx(Utils.generateIdx());
		subCategory.setName("카테고리하위이름");
		categoryService.addCategory(subCategory);
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 수정
	 */
	@Test
	public void modifyCategory() {
		// 카테고리 추가
		addCategory();
		
		List<Category> categories = categoryService.findCategories(null);
		assertEquals(1, categories.size());
		
		// 카테고리 수정
		Category category = categories.get(0);
		category.setIdx(category.getIdx());
		category.setName("카테고리이름수정");
		categoryService.modifyCategory(category);
		
		// 검증
		List<Category> categories2 = categoryService.findCategories(null);
		assertEquals(1, categories2.size());
		Category category2 = categories2.get(0);
		assertEquals(category.getName(), category2.getName());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 삭제 (해당 카테고리에 포스트 없는 경우)
	 */
	@Test
	public void removeCategoryByIdx() {
		// 카테고리 추가
		addCategory();
		
		List<Category> categories = categoryService.findCategories(null);
		assertEquals(1, categories.size());
		
		// 카테고리 삭제
		Category category = categories.get(0);
		categoryService.removeCategoryByIdx(category.getIdx());
		
		// 검증
		List<Category> categories2 = categoryService.findCategories(null);
		assertEquals(0, categories2.size());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 삭제 (해당 카테고리에 포스트 있는 경우) 
	 */
	@Test
	public void removeCategoryByIdx2() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 카테고리 추가
		addCategory();
		
		List<Category> categories = categoryService.findCategories(null);
		assertEquals(1, categories.size());
		Category category = categories.get(0);
		
		// 포스트 작성
		testHelper.writePost(member.getIdx(), category.getIdx());
		
		// 카테고리 삭제
		categoryService.removeCategoryByIdx(category.getIdx());
		
		// 검증
		List<Category> categories2 = categoryService.findCategories(null);
		assertEquals(0, categories2.size());
	}
	
	/**
	 * @date	: 2018. 4. 11.
	 * @TODO	: 카테고리 삭제 (해당 카테고리 및 하위 카테고리에 포스트 있는 경우) 
	 */
	@Test
	public void removeCategoryByIdx3() {
		// 회원 생성
		Member member = testHelper.signUpMember();
		
		// 상위, 하위 카테고리 추가
		addCategory2();
		
		List<Category> categories = categoryService.findCategories(null);
		assertEquals(1, categories.size());
		Category category = categories.get(0);
		List<Category> subCategories = categoryService.findCategories(category.getIdx(), AccessSpecifier.TOTAL);
		assertEquals(1, subCategories.size());
		Category subCategory = subCategories.get(0);
		
		// 포스트 작성
		Post post = testHelper.writePost(member.getIdx(), category.getIdx());
		Post post2 = testHelper.writePost(member.getIdx(), subCategory.getIdx());
		
		// 코멘트 작성
		testHelper.writeComment(member.getIdx(), post.getIdx());
		testHelper.writeComment(member.getIdx(), post2.getIdx());
		
		// 카테고리 삭제
		categoryService.removeCategoryByIdx(category.getIdx());
		
		// 검증
		List<Category> categories2 = categoryService.findCategories(null);
		assertEquals(0, categories2.size());
	}
}
