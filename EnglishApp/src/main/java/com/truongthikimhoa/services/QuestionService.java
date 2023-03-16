/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.truongthikimhoa.services;

import com.truongthikimhoa.pojo.Category;
import com.truongthikimhoa.pojo.Choice;
import com.truongthikimhoa.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionService {

    public boolean addQuestion(Question q, List<Choice> choices) throws SQLException {
        try ( Connection conn = JDBCUtils.createConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO question(id, content, category_id) VALUES(?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, q.getId());
            stm.setString(2, q.getContent());
            stm.setInt(3, q.getCategory_id());
            int r = stm.executeUpdate();
            
            for (Choice c: choices) {
                sql = "INSERT INTO choice(id, content, is_correct, question_id) VALUES (?, ?, ?, ?)";
                PreparedStatement stm1 = conn.prepareCall(sql);
                stm1.setString(1, c.getId());
                stm1.setString(2, c.getContent());
                stm1.setBoolean(3, c.isCorrect());
                stm1.setString(4, q.getId());
                
                stm1.executeUpdate();
            }
            
            conn.commit();
            return r > 0;
        }
    }
    
    public List<Question> getQuestions(String kw) throws SQLException {
        List<Question> list = new ArrayList<>();
        try (Connection conn = JDBCUtils.createConn()) {
            String sql = "SELECT * FROM question";
            if(kw!=null && !kw.isEmpty())
                sql += " WHERE content like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if(kw!=null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question quest = new Question(rs.getString("id"),
                        rs.getString("content"), rs.getInt("category_id"));
                list.add(quest);
            }
        }
        return list;
    }
    
    public boolean deleteQuestion(String questionId) throws SQLException {
        try (Connection conn = JDBCUtils.createConn()) {
            String sql = "DELETE FROM question WHERE id= ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, questionId);
            
            return stm.executeUpdate() > 0;
        }
    }
}
