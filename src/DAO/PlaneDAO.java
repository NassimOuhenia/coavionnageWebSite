package DAO;

import model.Plane;

import java.util.List;

public interface PlaneDAO {

    /**
     *
     * @return List of planes
     */
    List<Plane> getPlanes();

    /**
     * Insert a new plan in the DB
     * @param plane
     * @return The id of the new plane
     */
    int create(Plane plane);



}
