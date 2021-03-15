/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LPII02.Dal.Repositories;

import LPII02.Dal.Orm.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public abstract class BaseRepository<T> {

    protected Class<T> _class;
    protected Session _session;

    public BaseRepository(Class<T> typeClass) {
        this._class = typeClass;
        this._session = HibernateUtil.getSessionFactory().openSession();
    }

    public BaseRepository(Class<T> typeClass, Session session) {
        this._class = typeClass;
        this._session = session;
    }

    public void insert(T model, boolean close) {
        this._session.save(model);

        if (close) {
            this._session.close();
        }
    }

    public void insert(List<T> models, boolean close) {
        Transaction transaction = null;

        try {
            transaction = this._session.getTransaction();

            if (transaction == null) {
                transaction = this._session.beginTransaction();
            }

            for (T item : models) {
                this._session.save(item);
            }

            transaction.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        if (close) {
            this._session.close();
        }
    }

    public void update(T model, boolean close) {
        this._session.update(model);

        if (close) {
            this._session.close();
        }
    }

    public void update(List<T> models, boolean close) {
        Transaction transaction = null;

        try {
            transaction = this._session.getTransaction();

            if (transaction == null) {
                transaction = this._session.beginTransaction();
            }

            for (T item : models) {
                this._session.update(item);
            }

            transaction.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        if (close) {
            this._session.close();
        }
    }

    public void delete(T model, boolean close) {
        this._session.delete(model);

        if (close) {
            this._session.close();
        }
    }

    public void delete(List<T> models, boolean close) {
        Transaction transaction = null;

        try {
            transaction = this._session.getTransaction();

            if (transaction == null) {
                transaction = this._session.beginTransaction();
            }

            for (T item : models) {
                this._session.delete(item);
            }

            transaction.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        if (close) {
            this._session.close();
        }
    }

    public T get(int id) {
        return null;
    }

    public List<T> get(int[] ids) {
        return null;
    }
}