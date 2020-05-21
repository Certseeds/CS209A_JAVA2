/*  Java2RESTfulCorpusPlatform-Demo 
    Copyright (C) 2020 nanoseeds
    
    Java2RESTfulCorpusPlatform-Demo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Java2RESTfulCorpusPlatform-Demo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/Java2RESTfulCorpusPlatform-Demo
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-21 21:06:56
 * @LastEditors : nanoseeds
 */
package model;

public class summary {
    String md5;
    int length;
    String preview;

    public summary(String md5, int length, String preview) {
        this.md5 = md5;
        this.length = length;
        this.preview = preview;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    @Override
    public String toString() {
        return "summary {" +
                "md5='" + this.getMd5() + '\'' +
                ", length=" + this.getLength() + '\'' +
                ", preview=" + this.getPreview() + '}';

    }
}
