package com.kanaja.category.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kanaja.category.model.Category;
import com.kanaja.category.model.CategoryImpl;

/**
 * 
 * @author vikas_c
 *
 */
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


	public void save(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
		session.flush();
	}

	public Category getCategoryById(Integer categoryId) {
		Session session = sessionFactory.getCurrentSession();
		return (Category) session.get(CategoryImpl.class, categoryId);
	}

	@SuppressWarnings("unchecked")
	public Category getParentCategory(Integer categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CategoryImpl where parentCategory = :parentCategory");
		query.setParameter("parentCategory",categoryId);
		List<Category> categoryList = (List<Category>) query.list() ;
		if(categoryList.size()>0)
			return categoryList.get(0);
		else
			return null ;
	}
	
	@SuppressWarnings("unchecked")
	public Category getCategoryByName(String category) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CategoryImpl where name = :name");
		query.setParameter("name",category);
		List<Category> categoryList = (List<Category>) query.list() ;
		if(categoryList.size()>0)
			return categoryList.get(0);
		else
			return null ;
	}

	public void remove(Integer categoryId) {
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete CategoryImpl where id = :id" );
		query.setParameter("id", categoryId);
		query.executeUpdate();
	}

}
