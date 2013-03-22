package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.GroupCodes;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 03/03/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroupDataLayer implements IGroupDataLayer {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource() {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GroupCodes createGroup(String owner, String groupName) {

        String sqlQuery = "INSERT INTO `group` VALUES (\'"
                            + owner+groupName + "\',\'"
                            + groupName + "\',\'"
                            + owner
                            + "\',\'\',\'"
                            + owner
                            +"');";
        System.out.println(sqlQuery);
        try {
            this.jdbcTemplate.execute(sqlQuery);
        } catch (BadSqlGrammarException e) {
            return GroupCodes.SQL_SYNTAX_ERROR;
        } catch (DataRetrievalFailureException e) {
            return GroupCodes.DATA_RETRIEVAL_ERROR;
        } catch (CannotAcquireLockException e) {
            return GroupCodes.CAN_NOT_ACQUIRE_LOCK;
        } catch (DataAccessResourceFailureException e) {
            return GroupCodes.DB_CONNECTION_ERROR;
        }

        return GroupCodes.GROUP_CREATED;
    }

    public GroupCodes checkExistGroup(String groupId) {
        int size = 0;

        try {
            size = this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM `group` WHERE group_id = \'"
                    + groupId
                    + "\'");
        } catch (BadSqlGrammarException e) {
            return GroupCodes.SQL_SYNTAX_ERROR;
        } catch (DataRetrievalFailureException e) {
            return GroupCodes.DATA_RETRIEVAL_ERROR;
        } catch (CannotAcquireLockException e) {
            return GroupCodes.CAN_NOT_ACQUIRE_LOCK;
        } catch (DataAccessResourceFailureException e) {
            return GroupCodes.DB_CONNECTION_ERROR;
        }
        if(size > 0) {
            return GroupCodes.GROUP_ALREADY_EXIST;
        }
        return GroupCodes.GROUP_CREATED;
    }

    public String getMembers(String groupId) {
        int size = 0;

        String sqlQuery = "SELECT * FROM `group` WHERE group_id = \'"
                            + groupId
                            + "\'";
        try {

            System.out.println(this.jdbcTemplate.queryForMap(sqlQuery));
        } catch (BadSqlGrammarException e) {
            return GroupCodes.SQL_SYNTAX_ERROR.toString();
        } catch (DataRetrievalFailureException e) {
            return GroupCodes.DATA_RETRIEVAL_ERROR.toString();
        } catch (CannotAcquireLockException e) {
            return GroupCodes.CAN_NOT_ACQUIRE_LOCK.toString();
        } catch (DataAccessResourceFailureException e) {
            return GroupCodes.DB_CONNECTION_ERROR.toString();
        }

        return "";
    }
}
