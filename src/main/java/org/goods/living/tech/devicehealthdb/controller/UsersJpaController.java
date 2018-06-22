/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.goods.living.tech.devicehealthdb.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.goods.living.tech.devicehealthdb.dao.Stats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.goods.living.tech.devicehealthdb.controller.exceptions.IllegalOrphanException;
import org.goods.living.tech.devicehealthdb.controller.exceptions.NonexistentEntityException;
import org.goods.living.tech.devicehealthdb.dao.Users;

/**
 *
 * @author ernestmurimi
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getStatsCollection() == null) {
            users.setStatsCollection(new ArrayList<Stats>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Stats> attachedStatsCollection = new ArrayList<Stats>();
            for (Stats statsCollectionStatsToAttach : users.getStatsCollection()) {
                statsCollectionStatsToAttach = em.getReference(statsCollectionStatsToAttach.getClass(), statsCollectionStatsToAttach.getId());
                attachedStatsCollection.add(statsCollectionStatsToAttach);
            }
            users.setStatsCollection(attachedStatsCollection);
            em.persist(users);
            for (Stats statsCollectionStats : users.getStatsCollection()) {
                Users oldUserIdOfStatsCollectionStats = statsCollectionStats.getUserId();
                statsCollectionStats.setUserId(users);
                statsCollectionStats = em.merge(statsCollectionStats);
                if (oldUserIdOfStatsCollectionStats != null) {
                    oldUserIdOfStatsCollectionStats.getStatsCollection().remove(statsCollectionStats);
                    oldUserIdOfStatsCollectionStats = em.merge(oldUserIdOfStatsCollectionStats);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            Collection<Stats> statsCollectionOld = persistentUsers.getStatsCollection();
            Collection<Stats> statsCollectionNew = users.getStatsCollection();
            List<String> illegalOrphanMessages = null;
            for (Stats statsCollectionOldStats : statsCollectionOld) {
                if (!statsCollectionNew.contains(statsCollectionOldStats)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stats " + statsCollectionOldStats + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Stats> attachedStatsCollectionNew = new ArrayList<Stats>();
            for (Stats statsCollectionNewStatsToAttach : statsCollectionNew) {
                statsCollectionNewStatsToAttach = em.getReference(statsCollectionNewStatsToAttach.getClass(), statsCollectionNewStatsToAttach.getId());
                attachedStatsCollectionNew.add(statsCollectionNewStatsToAttach);
            }
            statsCollectionNew = attachedStatsCollectionNew;
            users.setStatsCollection(statsCollectionNew);
            users = em.merge(users);
            for (Stats statsCollectionNewStats : statsCollectionNew) {
                if (!statsCollectionOld.contains(statsCollectionNewStats)) {
                    Users oldUserIdOfStatsCollectionNewStats = statsCollectionNewStats.getUserId();
                    statsCollectionNewStats.setUserId(users);
                    statsCollectionNewStats = em.merge(statsCollectionNewStats);
                    if (oldUserIdOfStatsCollectionNewStats != null && !oldUserIdOfStatsCollectionNewStats.equals(users)) {
                        oldUserIdOfStatsCollectionNewStats.getStatsCollection().remove(statsCollectionNewStats);
                        oldUserIdOfStatsCollectionNewStats = em.merge(oldUserIdOfStatsCollectionNewStats);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Stats> statsCollectionOrphanCheck = users.getStatsCollection();
            for (Stats statsCollectionOrphanCheckStats : statsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Stats " + statsCollectionOrphanCheckStats + " in its statsCollection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Users findUsers(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
