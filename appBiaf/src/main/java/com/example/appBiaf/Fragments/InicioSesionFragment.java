package com.example.appBiaf.Fragments;

import android.content.Intent;
import android.os.Bundle;
import pojos.Usuario;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appBiaf.Cliente;
import com.example.appBiaf.R;
import com.example.appBiaf.databinding.FragmentHomeBinding;
import com.example.appBiaf.databinding.FragmentInicioSesionBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;



public class InicioSesionFragment extends Fragment {




    TextInputEditText us, contra;
    private FragmentInicioSesionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioSesionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        Button iniciar = root.findViewById(R.id.buttonLogin);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //coge el valor de los campos usuario y contraseña
                String u = us.getText().toString();
                String p = contra.getText().toString();
                if(u.equals("")||p.equals("")){
                    //si el usuario o contraseña esta vacío sale un toast indicandolo
                    Snackbar.make(view,getResources().getString(R.string.usucontravacio), Snackbar.LENGTH_SHORT).show();
                } else {
                    Usuario usuario = new Usuario();
                    usuario.setUser(u);
                    usuario.setContra(p);
                    Cliente cliente = new Cliente();
                    cliente.setOpcion("2");
                    cliente.setUsuario(usuario);
                    cliente.start();

                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", usuario.getUser());
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_nav_iniciosesion_to_nav_registro);
                    Navigation.findNavController(view).navigate(R.id.action_nav_iniciosesion_to_nav_perfil, bundle);
                    if (usuario.getMail() == null) {
                        Snackbar.make(view, getResources().getString(R.string.nousuario), Snackbar.LENGTH_SHORT).show();

                    }
                }

        }
        });


        
        // Para ir al resgistro
        TextView text = root.findViewById(R.id.textView2);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_iniciosesion_to_nav_registro);
            }
        });




        return root;




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}