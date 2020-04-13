/* CS209A_JAVA2/week08
   Copyright (C) 2020  nanoseeds

   CS209A_JAVA2/week08 is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   CS209A_JAVA2/week08 is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.
   */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2/week08
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-04-12 21:50:11
 * @LastEditors : nanoseeds
 */
package week08;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grid_n_m_gene")
public class grid_n_m_gene_warpper {
    private grid_n_m_gene gnmgs;

    @XmlElement(name = "gnmg")
    public grid_n_m_gene getGrid_n_m_gene() {
        return this.gnmgs;
    }

    public void setGrid_n_m_gene(grid_n_m_gene gnmg) {
        this.gnmgs = gnmg;
    }
}
