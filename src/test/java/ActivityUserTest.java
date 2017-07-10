
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActivityUserTest {
	ProcessEngine processEngine=null;
	IdentityService identityservice=null;
	
	@Before
	public void  before(){
		processEngine= ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("config/spring/_applicationContext-activiti-standalone.xml").buildProcessEngine();
		identityservice= processEngine.getIdentityService();
	}
	@Test
	public void ProcessTest(){
		
		User user=identityservice.createUserQuery().userId("1").singleResult();
		if(user==null){
           user=identityservice.newUser("1");
			user.setFirstName("翁");
			user.setLastName("绍辉");
			user.setEmail("whengshaohui@163.com");
			user.setPassword("123456");
			identityservice.saveUser(user);
			Assert.assertNotNull(user);
		}else{
			System.out.println(user.getFirstName());
		}
		
		Group group=identityservice.createGroupQuery().groupId("group_1").singleResult();
		if(group==null){
			group=identityservice.newGroup("group_1");
			group.setName("工作流程组");
			group.setType("group_1");
			identityservice.saveGroup(group);
		}else{
			System.out.println(group.getName());
			group.setType("group_2");
			identityservice.saveGroup(group);
		}
		
		
		identityservice.createMembership(user.getId(), group.getId());
		
		

		
		
		
		
	}

}