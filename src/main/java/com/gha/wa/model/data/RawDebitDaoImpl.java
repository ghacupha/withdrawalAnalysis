package com.gha.wa.model.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.Row;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gha.wa.api.RawDebitDao;
import com.gha.wa.model.MonthlyDebit;
import com.gha.wa.model.RawDebit;

public class RawDebitDaoImpl implements RawDebitDao{

	private final Logger log = LoggerFactory.getLogger(RawDebitDaoImpl.class);
	
	private static final RawDebitDao instance = new RawDebitDaoImpl();

	private EntityManagerFactory emFactory = 
			Persistence.createEntityManagerFactory("Eclipselink_JPA");

	private RawDebitDaoImpl() {

		log.debug("RawDebitDao created : {}",this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyDebit> getMontlyDebits() {
		
		EntityManager em = getEntityManager();
		
		List<MonthlyDebit> monthlyDebits = null;
		
		try {
			
			monthlyDebits =em.createQuery(
					"SELECT "
					+ "new com.gha.wa.model.MonthlyDebit(a.month, SUM(a.consAmount)) "
					+ "FROM RawDebit a "
					+ "GROUP BY a.month")
					.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return monthlyDebits;
	}
	
	@Override
	public void saveAllMonths(Optional<Collection<MonthlyDebit>> monthlyDebit) {

		log.debug("saveAllMonths collection method has been called wih {} as args"
				+ "with a size of {}", monthlyDebit.get().getClass(),monthlyDebit.get().size());

		EntityManager em = getEntityManager();

		try {

			log.debug("Trying to persist {} collection using {}",
					monthlyDebit.get().getClass(), em);

			em.getTransaction().begin();

			Iterator<MonthlyDebit> monthlyDebitIter = monthlyDebit.get().iterator();

			log.debug("Transaction has begun with iterator : {}",
					monthlyDebitIter.getClass());

			while(monthlyDebitIter.hasNext()) {

				MonthlyDebit monthly = monthlyDebitIter.next();						

				em.persist(monthly);

			}

			em.getTransaction().commit();

		} catch (Exception e) {
			log.error("Exception thrown :{}, while trying to persist {}"
					+ " caused by : {} at the trace point : {}",
					e.getMessage(),monthlyDebit.get().getClass(),e.getCause(),e.getStackTrace());
			e.printStackTrace();
		}

	}

	@Override
	public void saveAll(Optional<Collection<RawDebit>> rawDebits) {

		log.debug("saveAll collection method has been called wih {} as args"
				+ "with a size of {}", rawDebits.get().getClass(),rawDebits.get().size());

		EntityManager em = getEntityManager();

		try {

			log.debug("Trying to persist {} collection using {}",
					rawDebits.get().getClass(), em);

			em.getTransaction().begin();

			Iterator<RawDebit> rawDebitIter = 
					rawDebits.get().iterator();

			log.debug("Transaction has begun with iterator : {}",
					rawDebitIter.getClass());

			while(rawDebitIter.hasNext()) {

				RawDebit rawDebit = rawDebitIter.next();						

				em.persist(rawDebit);

			}

			em.getTransaction().commit();

		} catch (Exception e) {
			log.error("Exception thrown :{}, while trying to persist {}"
					+ " caused by : {} at the trace point : {}",
					e.getMessage(),rawDebits.get().getClass(),e.getCause(),e.getStackTrace());
			e.printStackTrace();
		}

	}

	@Override
	public void createRawDebit(RawDebit rawDebit) {

		EntityManager em = getEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(rawDebit);

			em.getTransaction().commit();

		} catch (Exception e) {
			log.error("Exception thrown :{}, while trying to persist {}"
					+ " caused by : {} at the trace point : {}",
					e.getMessage(),rawDebit,e.getCause(),e.getStackTrace());
			e.printStackTrace();
		}
	}

	@Override
	public RawDebit getByIdRawDebit(int id) {

		Optional<RawDebit> rawDebit = Optional.empty();

		EntityManager em = getEntityManager();

		try {
			log.debug("Trying to get rawDebit object id : {} from {}",
					id,em);
			rawDebit = Optional.of(em.find(RawDebit.class, id));
		} catch (Exception e) {
			log.error("Error getting object of class {}, id # :{}, from {} "
					+ "caused by {}",RawDebit.class,id,em,e.getCause());
			e.printStackTrace();
		}


		return rawDebit.get();
	}

	@Override
	public void updateRawDebitId(int id,RawDebit rawDebitVo) {
		EntityManager em = getEntityManager();
		Optional<RawDebit> updebit = Optional.empty();

		try {
			log.debug("Trying to get rawDebit object id : {} from {}",
					id,em);
			em.getTransaction().begin();

			updebit = Optional.of(em.find(RawDebit.class, id));

			updebit.get().copy(rawDebitVo);

			em.getTransaction().commit();			

		} catch (Exception e) {

			em.getTransaction().rollback();

			log.error("Error updating object of class {}, id # :{}, from {} "
					+ "caused by {}",RawDebit.class,id,em,e.getCause());
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRawDebit(int id) {
		EntityManager em = getEntityManager();

		em.remove(em.find(RawDebit.class, id));

		em.getTransaction().commit();

	}
	
	
	@Override
	public void saveAll(List<Row> rowList) {
		
		EntityManager em = getEntityManager();
		
		log.trace("Saving rowList of size {} in database, with {}", rowList.size(), em);
		
		//TODO
	}
	
	private Optional<Collection<RawDebit>> acquireRawDebitList(List<Row> rowList, String className){
		
		Collection<RawDebit> rawDebits = new FastList<>();
		
		log.trace("Acquiring a list of domain object from the row list");
		
		for(Row row : rowList) {
			
			Class<?> clazz = Class.forName(className);
			
			 // Search for an "appropriate" constructor.
		    for (Constructor<?> ctor : clazz.getConstructors()) {
		        Class<?>[] paramTypes = ctor.getParameterTypes();

		        // If the arity matches, let's use it.
		        if (args.size() == paramTypes.length) {

		            // Convert the String arguments into the parameters' types.
		            Object[] convertedArgs = new Object[args.size()];
		            for (int i = 0; i < convertedArgs.length; i++) {
		                convertedArgs[i] = convert(paramTypes[i], args.get(i));
		            }

		            // Instantiate the object with the converted arguments.
		            return ctor.newInstance(convertedArgs);
		        }
			
		}
		
		return Optional.of(rawDebits);
	}

	private EntityManager getEntityManager() {

		log.debug("Creating the an entityManager from : {}",emFactory);

		EntityManager em = null;

		try {
			em = emFactory.createEntityManager();
		} catch (Exception e) {
			log.error("Exception : {}, while trying to create EntityManager"
					+ "from the emFactory, caused by {} at stack trace : {}",
					e.getMessage(),e.getCause(),e.getStackTrace());
			e.printStackTrace();
		}


		return em;
	}

	public static RawDebitDao getInstance() {
		return instance;
	}

}
