package com.kanaja.document.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kanaja.category.model.Category;
import com.kanaja.document.model.Prefix;
import com.kanaja.document.model.PrefixImpl;
/**
 * 
 * @author vikas_c
 *
 */
@Repository("prefixDAO")
public class PrefixDAOImpl implements PrefixDAO {

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public Integer save(Prefix prefix) {
		Session session = sessionFactory.getCurrentSession();
		session.save(prefix);
		session.flush();
		return prefix.getId() ;
	}

	public Prefix getPrefixById(Integer prefixId) {
		Session session = sessionFactory.getCurrentSession();
		return (Prefix)session.get(PrefixImpl.class, prefixId);
	}

	public void remove(Prefix prefix) {
		// TODO Auto-generated method stub
		Session session =sessionFactory.getCurrentSession();
		session.delete(prefix);
		session.flush();
	}
	
	public void update(Prefix prefix){
		Session session = sessionFactory.getCurrentSession();
		session.update(prefix);
		session.flush();
	}

	public List<Prefix> getDocumentPrefixList(Integer documentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Prefix getPrefixByName(String prefix) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PrefixImpl where prefix = :prefix");
		query.setParameter("prefix",prefix);
		List<Prefix> prefixList = (List<Prefix>) query.list() ;
		if(prefixList.size()>0)
			return prefixList.get(0);
		else
			return null ;
	}

}
