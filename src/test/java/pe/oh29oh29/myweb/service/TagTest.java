package pe.oh29oh29.myweb.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.PostDao;
import pe.oh29oh29.myweb.dao.PostTagRelationDao;
import pe.oh29oh29.myweb.dao.TagDao;
import pe.oh29oh29.myweb.model.Post;
import pe.oh29oh29.myweb.model.PostTagRelation;
import pe.oh29oh29.myweb.model.Tag;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TagTest {

	@Autowired TagDao tagDao;
	@Autowired PostDao postDao;
	@Autowired PostService postService;
	@Autowired PostTagRelationDao postTagRelationDao;
	
	@Before
	public void setUp() throws Exception {
		postTagRelationDao.deleteAllPostTagRelations();
		tagDao.deleteAllTags();
		
	}

	@Test
	public void test() {
		Tag tag = new Tag();
		tag.setIdx(Utils.generateIdx());
		tag.setName("TAG테스트");
		tagDao.insertTag(tag);
		
		Tag tag2 = new Tag();
		tag2.setIdx(Utils.generateIdx());
		tag2.setName("JAVA");
		tagDao.insertTag(tag2);
		
		Tag tag3 = new Tag();
		tag3.setIdx(Utils.generateIdx());
		tag3.setName("C");
		tagDao.insertTag(tag3);
		
		List<Tag> tags = tagDao.selectTag(null);
		List<Post> posts = postDao.selectPost(null);
		
		PostTagRelation relation = new PostTagRelation();
		relation.setPostIdx(posts.get(0).getIdx());
		relation.setTagIdx(tags.get(0).getIdx());
		postTagRelationDao.insertPostTagRelation(relation);
		
		PostTagRelation relation2 = new PostTagRelation();
		relation2.setPostIdx(posts.get(0).getIdx());
		relation2.setTagIdx(tags.get(1).getIdx());
		postTagRelationDao.insertPostTagRelation(relation2);
		
//		PostTagRelation relation3 = new PostTagRelation();
//		relation3.setPostIdx(posts.get(1).getIdx());
//		relation3.setTagIdx(tags.get(2).getIdx());
//		postTagRelationDao.insertPostTagRelation(relation3);
		
		
	}

}
