package dao;
import modele.Disponibilites;
import java.util.List;

public interface DateDisponibleDAO
{
    List<Disponibilites> getDisponibilitesParLogement(int idLogement);
}
