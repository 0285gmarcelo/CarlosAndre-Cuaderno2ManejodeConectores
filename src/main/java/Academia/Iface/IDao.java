/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package Academia.Iface;
import java.util.List;

/**
 *
 * @author isard
 */
public interface IDao<T,V> {
    boolean createRecord(T model);
    
    T readRecord(V idModel);
    
    boolean updateRecord(T model, V idModel);
    
    boolean deleteRecord(V idModel);
    
    List<T> readRecords();
}
