package com.kanaja.index.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.kanaja.index.model.Index;
import com.kanaja.search.helper.AdvancedSearchCriteria;

@Repository("indexDAO")
public class IndexDAOImpl implements IndexDAO {

	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void save(Index index) {
		Session session = sessionFactory.getCurrentSession() ;
		session.save(index);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Index> getIndexDetails(String prefix, AdvancedSearchCriteria criteria,String searchIn) {
		StringBuilder q = new StringBuilder() ;
		if(searchIn.equalsIgnoreCase("title")){
			q.append("from TitleIndexImpl where prefixPK.prefix.prefix = :prefix");
		}
		else {
			q.append("from IndexImpl where prefixPK.prefix.prefix = :prefix");
		}
		
		addCriteriaToQuery(criteria,q);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(q.toString());
		query.setParameter("prefix",prefix);
		addCriteriaToQuery(criteria,query);
		List<Index> indexList = (List<Index>) query.list() ;
		return indexList ;
	}

	protected void addCriteriaToQuery(AdvancedSearchCriteria criteria , StringBuilder query){
		if(criteria!=null){
			if(criteria.getType()!=null){
				query.append(" and type = :type");
			}
			if(criteria.getAuthor()!=null){
				query.append(" and author.name like '%" + criteria.getAuthor() +"%'");
			}
			if(criteria.getCategoryId()!=null){
				query.append(" and prefixPK.category.id = :categoryId");
			}
			if(criteria.getFromDate()!=null && criteria.getToDate()!=null){
				query.append(" and prefixPK.document.indexedDate between :fromDate and :toDate");
			}
		}
	}
	protected void addCriteriaToQuery(AdvancedSearchCriteria criteria , Query query){
		if(criteria!=null){
			if(criteria.getType()!=null){
				query.setParameter("type",criteria.getType());
			}
			if(criteria.getCategoryId()!=null){
				query.setParameter("categoryId",criteria.getCategoryId());
			}
			if(criteria.getFromDate()!=null && criteria.getToDate()!=null){
				query.setParameter("fromDate", criteria.getFromDate());
				query.setParameter("toDate" , criteria.getToDate());
			}
		}
	}
	public void remove(Integer indexId) {
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete IndexImpl where id = :id" );
		query.setParameter("id", indexId);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPrefixByDocument(Integer documentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select distinct prefixPK.prefix.prefix from IndexImpl where prefixPK.document.id = :documentId");
		query.setParameter("documentId",documentId);
		List<String> rows = (List<String>) query.list() ;
		return rows ;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTitlePrefixByDocument(Integer documentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select distinct prefixPK.prefix.prefix from TitleIndexImpl where prefixPK.document.id = :documentId");
		query.setParameter("documentId",documentId);
		List<String> rows = (List<String>) query.list() ;
		return rows ;
	}
	
	public void removeIndexByDocument(Integer documentId){
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete IndexImpl where prefixPK.document.id = :id" );
		query.setParameter("id", documentId);
		query.executeUpdate();
	}
	
	public void removeTitleIndexByDocument(Integer documentId){
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("delete TitleIndexImpl where prefixPK.document.id = :id" );
		query.setParameter("id", documentId);
		query.executeUpdate();
	}
	

}
