package com.clipper.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clipper.model.PostImage;

@Repository
public class PostImageDao implements Dao<PostImage, Integer> {

	private SessionFactory factory;
	
	@Autowired
	public PostImageDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	public PostImageDao() {}
	
	@Override
	public List<PostImage> findAll() {
		Session sess = factory.openSession();
		List<PostImage> list = sess.createQuery("from PostImage", PostImage.class).list();
		sess.close();
		return list;
	}

	@Override
	public PostImage findById(Integer i) {
		Session sess = factory.openSession();
		PostImage result = sess.createQuery("from PostImage where id = " + i, PostImage.class).list().get(0);
		sess.close();
		return result;
	}

	@Override
	public PostImage update(PostImage t) {
		SessionFactory sesfact = factory;
		Session sess = sesfact.openSession();
		Transaction tx = sess.beginTransaction();
		sess.merge(t);
		tx.commit();
		sess.close();
		return t;
	}

	@Override
	public PostImage save(PostImage t) {
		SessionFactory sesfact = factory;
		Session sess = sesfact.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(t);
		tx.commit();
		sess.close();
		return t;
	}

	@Override
	public PostImage delete(Integer i) {
		PostImage pi = findById(i);
		
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(pi);
		tx.commit();
		sess.close();
		return pi;
	}
	public void deleteAll() {
		Session sess = factory.openSession();
		sess.createQuery("delete from PostImage");
		sess.close();
	}
	
	
}