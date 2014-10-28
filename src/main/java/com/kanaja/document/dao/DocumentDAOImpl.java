package com.kanaja.document.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kanaja.document.model.Document;
import com.kanaja.document.model.DocumentImpl;
import com.kanaja.document.model.Prefix;
import com.kanaja.search.helper.AdvancedSearchCriteria;

/**
 * 
 * @author vikas_c
 *
 */
@Repository("documentDAO")
public class DocumentDAOImpl implements DocumentDAO {
	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Document document) {
		Session session = sessionFactory.getCurrentSession();
		session.save(document);
		session.flush();

	}

	public Document getDocumentById(Integer documentId) {
		Session session =sessionFactory.getCurrentSession() ;
		return (Document) session.get(DocumentImpl.class, documentId);
	}

	public void remove(Integer documentId) {
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete DocumentImpl where id = :id" );
		query.setParameter("id", documentId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public Integer getDocumentCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(d) from DocumentImpl d");
		List<Long> countList = (List<Long>) query.list() ;
		if(countList.size()>0)
			return countList.get(0).intValue();
		else
			return 0 ;
	}
	
	@SuppressWarnings("unchecked")
	public Document getDocumentByURL(String url){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from DocumentImpl where url = :url");
		query.setParameter("url",url);
		List<Document> documentList = (List<Document>) query.list() ;
		if(documentList.size()>0)
			return documentList.get(0);
		else
			return null ;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Document> searchDocumentByCriteria(AdvancedSearchCriteria criteria){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder q = new StringBuilder("from DocumentImpl where 1=1") ;
		if(criteria!=null){
			if(criteria.getType()!=null){
				q.append(" and type = :type");
			}
			if(criteria.getAuthor()!=null){
				q.append(" and author like '%" + criteria.getAuthor() +"%'");
			}
			if(criteria.getFromDate()!=null && criteria.getToDate()!=null){
				q.append(" and indexedDate between :fromDate and :toDate");
			}
		}
		Query query = session.createQuery(q.toString());
		if(criteria!=null){
			if(criteria.getType()!=null){
				query.setParameter("type",criteria.getType());
			}
			if(criteria.getFromDate()!=null && criteria.getToDate()!=null){
				query.setParameter("fromDate", criteria.getFromDate());
				query.setParameter("toDate" , criteria.getToDate());
			}
		}
		List<Document> documentList = (List<Document>) query.list() ;
		return documentList ;
	}

	

}
