/*  CS209A_JAVA2 
    Copyright (C) 2020 nanoseeds
    
    CS209A_JAVA2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    CS209A_JAVA2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-31 20:29:30
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.java1_assignment6;

public class OrdinaryMovie extends Movie {

    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), "OrdinaryMovie");
    }

    @Override
    public double purchase(int arg) {
        return this.getPrice() *
                Math.min(arg, this.ticketsLeft);
    }
}
