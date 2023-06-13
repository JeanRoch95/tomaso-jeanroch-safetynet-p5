package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.FireStationCoveredDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.model.Firestation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FirestationService {

    /**
     * Retrieve a list of all firestation.
     *
     * <p>This method return a list of {@link Firestation}. Each Firestation contain firestation number
     * and address.
     * </p>
     *
     * @return a list of Firestation
     */
    List<Firestation> getAllFirestation();

    /**
     * Create a new firestation
     *
     * <p>This method create a new {@link Firestation}.
     * </p>
     *
     * @param firestation
     *
     * @return a list of {@link Firestation} representing the remaining firestation after creation.
     */
    Firestation createFirestation(Firestation firestation);

    /**
     * Delete firestation, based on address and number of station.
     *
     * @param address The address of the firestation to be deleted.
     * @param station The station Number of the firestation to be deleted.
     *
     * @return a list of {@link Firestation} representing the remaining firestation after deletion.
     */
    List<Firestation> deleteFirestation(String address, String station);

    /**
     * Updates an existing fire station with new information provided by a Firestation object.
     *
     * @param firestation The Firestation object containing updated information.
     * @param address The address of the firestation to be updated.
     * @param station The station of the firestation to be updated.
     *
     * @return The updated {@link Firestation} object if the operation is successful
     */
    Firestation updateFirestation(Firestation firestation, String address, String station);

    /**
     * Retrieve a list of person residing at a given address for fire incidents.
     *
     * <p>This method returns a list of {@link InfoPersonFireDTO}, each of which includes
     * detailed information about a person living at the specified address.
     * </p>
     *
     * @param address The address for which resident information is to be retrieved.
     *
     * @return A list of {@link InfoPersonFireDTO} representing persons residing at
     * the specified address.
     */
    List<InfoPersonFireDTO> getFireListPerson(String address);

    /**
     * Retrieves a list of addresses covered by a given fire station.
     *
     * <p>
     * This method returns a list of addresses that are covered by the specified fire station.
     * </p>
     *
     * @param station The identifier of the fire station for which covered addresses are to be retrieved.
     *                *
     * @return A list of addresses represented as strings. Each address is a location that is covered by
     * the specified fire station.
     */
    List<String> addressCoveredByStation(String station);

    /**
     * Retrieves a list of persons covered by the specified fire station.
     *
     * <p>
     * This method finds all the people who are served by a specific fire station, based on the station identifier
     * provided as the parameter. It returns a list of {@link FireStationCoveredDTO}.
     * </p>
     *
     * @param station A String representing the identifier of the fire station.
     * This is used to find all persons served by this station.
     *
     * @return A list of {@link FireStationCoveredDTO} objects, each representing a person served
     * by the specified fire station.
     */
    List<FireStationCoveredDTO> getPersonCoveredByFirestation(String station);

    /**
     * Retrieves a list of the address of all the firestation on database.
     *
     * <p>
     * This method is used to handle errors
     * </p>
     *
     * @return A list of String that contains all the address in the database
     */
    List<String> getListAddress();

    /**
     * Retrieves a list of the station number of all the firestation on database.
     *
     * <p>
     * This method is used to handle errors
     * </p>
     *
     * @return A list of String that contains all the station number in the database
     */
    List<String> getListStationNumber();

}
