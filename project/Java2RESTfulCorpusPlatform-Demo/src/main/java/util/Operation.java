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
 * @Date: 2020-05-20 18:06:41
 * @LastEditors : nanoseeds
 */
package util;

public enum Operation {

    UPLOAD, DOWNLOAD, COMPARE, EXISTS, FILES, BREAK, NOT_ANY_ONE;

    public static Operation parseOperation(String op) {
        switch (op.toLowerCase()) {
            case "upload": {
                return Operation.UPLOAD;
            }
            case "download": {
                return Operation.DOWNLOAD;
            }
            case "compare": {
                return Operation.COMPARE;
            }
            case "exists": {
                return Operation.EXISTS;
            }
            case "break": {
                return Operation.BREAK;
            }
            case "files": {
                return Operation.FILES;
            }
            default: {
                return Operation.NOT_ANY_ONE;
            }
        }
    }
}
