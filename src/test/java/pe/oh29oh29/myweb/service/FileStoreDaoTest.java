package pe.oh29oh29.myweb.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
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
import pe.oh29oh29.myweb.dao.FileStoreDao;
import pe.oh29oh29.myweb.model.FileStore;
import pe.oh29oh29.myweb.service.AttachedFileService.FileType;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class FileStoreDaoTest {

	@Autowired FileStoreDao fileStoreDao;
	
	@Before
	public void setUp() throws Exception {
		fileStoreDao.deleteAllFileStores();
	}

	@Test
	public void test() {
		FileStore fileStore = new FileStore();
		fileStore.setIdx(Utils.generateIdx());
		fileStore.setContextPath("imageInContents");
		fileStore.setRealPath("E:" + File.separator + "FileStore");
		fileStore.setType(FileType.IMAGE_IN_CONTENTS.intValue());
		fileStoreDao.insertFileStore(fileStore);
		
		List<FileStore> fileStores = fileStoreDao.selectFileStore(null);
		assertEquals(1, fileStores.size());
	}

}
