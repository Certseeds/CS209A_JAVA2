/* AddressApp 
   Copyright (C) 2020  nanoseeds

   AddressApp is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   AddressApp is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.
   */
/**
 * @Github: https://github.com/Certseeds/AddressApp
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-04-06 21:39:43
 * @LastEditors : nanoseeds
 */
package address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
// !!!should download by user itself.

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}