/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academia.Factory;

import Academia.Iface.IDao;
import Academia.Daos.*;
/**
 *
 * @author isard
 */
public class DaoFactory {
    public enum DaoType{
        ALUMNO, PROFESOR, ASIGNATURA, CURSO, AULA, MATRICULA, HORARIO
    }
    
    public static <T, V> IDao<T, V> getDao(DaoType daoType) {
        return switch (daoType) {
            case ALUMNO -> (IDao<T, V>) new AlumnoDao();                         
            case PROFESOR -> (IDao<T, V>) new ProfesorDao();
            case ASIGNATURA -> (IDao<T, V>) new AsignaturaDao();
            case CURSO -> (IDao<T, V>) new CursoDao();
            case AULA -> (IDao<T, V>) new AulaDao();
            case MATRICULA -> (IDao<T, V>) new MatriculaDao();
            case HORARIO -> (IDao<T, V>) new HorarioDao();
        };
    }
}
