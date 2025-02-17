package com.Loja.com.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Loja.com.Model.Usuario;
import com.Loja.com.Model.tipo_usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  Optional<Usuario> findByEmail(String email);
  Optional<Usuario> findByIsMainAdminTrue();
  List<Usuario> findByTipoUsuario(tipo_usuario tipo_usuario);

}
