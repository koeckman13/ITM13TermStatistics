package at.fhj.itm.pswe.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import at.fhj.itm.pswe.model.Word;

@Stateless
@Path("/word")
public class WordEndpoint{
	@PersistenceContext(unitName = "TermStatistics")
	private EntityManager em;
	
	@GET
	@Produces("application/json")
	public List<Word> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
	{
		TypedQuery<Word> findAllQuery = em.createQuery("SELECT DISTINCT w FROM Word w ORDER BY w.text", Word.class);
		
		if (startPosition != null)
	      {
	         findAllQuery.setFirstResult(startPosition);
	      }
	      if (maxResult != null)
	      {
	         findAllQuery.setMaxResults(maxResult);
	      }

		final List<Word> results = findAllQuery.getResultList();
		return results;
	}
	
	@GET
    @Path("/{id:[a-zA-Z][a-zA-Z]*}")
    @Produces("application/json")
    public Response findById(@PathParam("id") String id)
    {
       TypedQuery<Word> findByIdQuery = em.createQuery("SELECT DISTINCT w FROM Word w WHERE w.text = :text", Word.class);
       findByIdQuery.setParameter("text", id);
       Word entity;
       try
       {
          entity = findByIdQuery.getSingleResult();
       }
       catch (NoResultException nre)
       {
          entity = null;
       }
       if (entity == null)
       {
          return Response.status(Status.NOT_FOUND).build();
       }
       return Response.ok(entity).build();
   }
}