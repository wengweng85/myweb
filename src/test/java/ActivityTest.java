

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

public class ActivityTest {
	
	@Test
	public void ProcessTest(){
		ProcessEngine processEngine= ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("config/spring/_applicationContext-activiti-standalone.xml").buildProcessEngine();
		RepositoryService responseitysevice=processEngine.getRepositoryService();
		RuntimeService runtimeservice=processEngine.getRuntimeService();
		responseitysevice.createDeployment().addClasspathResource("bpmn/interview.bpmn").deploy();
		
		Map<String, Object> variables = new HashMap<String, Object>();  
	    variables.put("groupid", "groupid");
	        
	        
		String processid= runtimeservice.startProcessInstanceByKey("Interview",variables).getId();
		Assert.assertNotNull(processid);
		
		TaskService taskService = processEngine.getTaskService();
	    //�õ����Ե�����
	    System.out.println("\n***************�������̿�ʼ***************");

	   
        
	    List<Task> tasks = taskService.createTaskQuery().taskAssignee("groupid").list();
	    for (Task task : tasks) {
	        System.out.println("������Դ��������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }

	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    tasks = taskService.createTaskQuery().taskAssignee("����").list();
	    for (Task task : tasks) {
	        System.out.println("����������name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }

	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************�������̽���***************");

	    System.out.println("\n***************һ�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************һ�����̽���***************");

	    System.out.println("\n***************�������̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************�������̽���***************");

	    System.out.println("***************HR�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************HR�����̽���***************");

	    System.out.println("\n***************¼�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }

	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************¼�����̽���***************");

	    HistoryService historyService = processEngine.getHistoryService();
	    HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processid).singleResult();
	    System.out.println("\n���̽���ʱ�䣺"+historicProcessInstance.getEndTime());
	}

}