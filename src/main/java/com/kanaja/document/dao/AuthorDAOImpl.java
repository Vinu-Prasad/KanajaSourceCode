package com.kanaja.document.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kanaja.document.model.Author;
import com.kanaja.document.model.AuthorImpl;
import com.kanaja.document.model.Prefix;
/**
 * 
 * @author vikas_c
 *
 */
@Repository("authorDAO")
public class AuthorDAOImpl implements AuthorDAO {
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	

	@SuppressWarnings("unchecked")
	public Author getAuthorByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from AuthorImpl where name= :name");
		query.setParameter("name",name);
		List<Author> authorList = (List<Author>) query.list() ;
		if(authorList.size()>0)
			return authorList.get(0);
		else
			return null ;
	}

	public Author getAuthorById(Integer authorId) {
		Session session = sessionFactory.getCurrentSession();
		return (Author) session.get(AuthorImpl.class, authorId) ;
	}

	public void saveAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession() ;
		session.save(author);
		session.flush();

	}
	public void update(Author author){
		Session session = sessionFactory.getCurrentSession();
		session.update(author);
		session.flush();
	}

	public void removeAuthor(Integer authorId) {
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete AuthorImpl where id = :id" );
		query.setParameter("id", authorId);
		query.executeUpdate();
	}

	
}
