/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Conexion;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import java.io.File;

public class ConexionDb4o {

    public static ObjectContainer conectar() {
        new File("data").mkdirs();
        return Db4o.openFile("data/academia.db4o");
    }
}