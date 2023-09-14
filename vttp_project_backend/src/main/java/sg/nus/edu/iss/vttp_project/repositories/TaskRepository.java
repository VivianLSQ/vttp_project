package sg.nus.edu.iss.vttp_project.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp_project.models.Task;
import sg.nus.edu.iss.vttp_project.models.TaskDetails;

@Repository
public class TaskRepository {

    //CRUD for tasks and taskDetails 

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    //Insert SQL Statements here 
    private final String findAllTaskSQL = "select * from tasks"; 
    private final String addNewTaskSQL = "insert into tasks (task_id, content, date_added, due_date, notes) values (?, ?, ?, ?, ?)";
    private final String addTaskDetailsSQL = "insert into task_details (id, is_completed, category, priority) values (?, ?, ?, ?)";
    private final String updateTaskSql = "update tasks set content = ?, date_added  = ?, due_date  = ?, notes = ? where task_id = ?";
    private final String updateTaskDetailsSql = "update task_details set is_completed = ?, category = ?, priority = ? where id = ?"; 
    private final String deleteTaskById = "delete from tasks where task_id = ?";
    //private final String deleteTaskDetailsById = "delete from task_details where id = ?"; 


    //See all Tasks 
    public List<Task> getAllTasks() {
        List<Task> TaskList = new ArrayList<Task>();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllTaskSQL);

        while(rs.next()){
            Task task = new Task();
            task.setTaskId(rs.getInt("task_id"));
            task.setContent(rs.getString("content"));
            task.setDateAdded(rs.getDate("dateAdded"));
            task.setDueDate(rs.getDate("dueDate"));
            task.setNotes(rs.getString("notes"));

           TaskList.add(task);
        }
        return Collections.unmodifiableList(TaskList);
     } 

    //Create new Task entry
    public Integer addNewTask(Task task){
    KeyHolder generatedKey = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(addNewTaskSQL, new String[] {"task_id"});
                ps.setString(1, task.getContent());
                ps.setDate(2, task.getDateAdded()); //changed Date type to sql
                ps.setDate(3, task.getDueDate());
                ps.setString(4, task.getNotes());

                return ps;
            }
        }; 

        jdbcTemplate.update(psc, generatedKey);

        return generatedKey.getKey().intValue();
    }


    //Create new Task Detail entry
    public Boolean addTaskDetails(TaskDetails details) {
        int TaskDetailsCreated = jdbcTemplate.update(addTaskDetailsSQL, details.getId(), details.getIsCompleted(), details.getCategory(), details.getPriority());
        if (TaskDetailsCreated == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    //Update/edit existing Task entry
    public Integer updateTask(Task task) {
        int iTaskUpdated = 0;

        iTaskUpdated = jdbcTemplate.update(updateTaskSql, task.getContent(), task.getDateAdded(), task.getDueDate(), task.getNotes(), task.getTaskId());

        return iTaskUpdated;
    }

    //Update/edit existing Task Details entry
    public Integer updateTaskDetails(TaskDetails details) {
        int iTaskDetailsUpdated = 0;

        iTaskDetailsUpdated = jdbcTemplate.update(updateTaskDetailsSql,details.getIsCompleted(), details.getCategory(), details.getPriority(), details.getId());

        return iTaskDetailsUpdated;
    }


    //Delete existing Task entry
    public void deleteTask(Integer taskId) {
       
       jdbcTemplate.update(deleteTaskById, taskId); 
    }

    //Delete existing TaskDetails entry (Obselete as added ON DELETE CASCADE in SQL)
    // public void deleteTaskDetails(Integer id) {
       
    //    jdbcTemplate.update(deleteTaskDetailsById, id); 
    // }


}
