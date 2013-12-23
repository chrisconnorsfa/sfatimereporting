/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.freckle;

import com.scottishfriendly.timereporting.jsonservices.freckle.Entry;
import com.scottishfriendly.timereporting.jsonservices.freckle.Project;
import com.scottishfriendly.timereporting.jsonservices.freckle.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chriconn
 */
public interface IFreckleService {
    public List<User> getAllUsers();
    public List<Project> getAllProjects();
    public List<Entry> getEntriesByUserId(Integer id);
    public List<Entry> getEntriesByTags(List<Tag>tags);
    public List<Entry> getEntries(Integer id);
    public List<Entry> getEntries(Integer id, Integer projectid, Date from, Date to);
    public Project getProjectByName(String name);
    public User getUserByName(String name);
    public Project getProjectById(Integer id);
    public User getUserById(Integer id);
    public boolean delete(Long id);
    public boolean create(PostEntry entry);
}
