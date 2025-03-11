package com.edteam.reservations.dao;

import com.edteam.reservations.dto.SearchReservationCriteriaDTO;
import com.edteam.reservations.model.Reservation;
import com.edteam.reservations.specification.ReservationSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Repository
public class ReservationDao implements IReservationDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Reservation> findAll(SearchReservationCriteriaDTO criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = query.from(Reservation.class);
        Predicate predicate = ReservationSpecification.withSearchCriteria(criteria).toPredicate(root,query,criteriaBuilder);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class,id);
        return Optional.of(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (Objects.nonNull(reservation.getId())){
            entityManager.merge(reservation);
            entityManager.flush();
        } else {
            entityManager.persist(reservation);
            entityManager.flush();
        }
        return reservation;
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class,id);
        if (Objects.nonNull(reservation)){
            entityManager.remove(reservation);
            entityManager.flush();
        }
    }

    @Override
    public boolean existsById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class,id);
        return Objects.nonNull(reservation);
    }
}
