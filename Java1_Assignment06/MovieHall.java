/*  leetcode/java
    Copyright (C) 2020 nanoseeds

    leetcode_java is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    leetcode_java is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */

/**
 * @Github: https://github.com/Certseeds/leetcode/java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-31 21:20:08
 * @LastEditors : nanoseeds
 */

public class MovieHall {
    public int getOrder() {
        return order;
    }

    public int getSize() {
        return size;
    }

    private int order;
    private int size;

    public MovieHall(int order, int size) {
        this.order = order;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", order, size);
    }
}
