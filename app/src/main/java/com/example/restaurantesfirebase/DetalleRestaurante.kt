package com.example.restaurantesfirebase

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.common.io.Files.map
import com.squareup.picasso.Picasso

class DetalleRestaurante : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var map: GoogleMap
    var x = 0.0
    var y = 0.0
    var nombre = ""

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_restaurante)

        val tvNombreDetalle = findViewById<TextView>(R.id.tvNombreDetalle)
        val tvAnioDetalle = findViewById<TextView>(R.id.tvAnioDetalle)
        val tvCalifDetalle = findViewById<TextView>(R.id.tvCalifDetalle)
        val tvCostoDetalle = findViewById<TextView>(R.id.tvCostoDetalle)
        val tvReseniaDetalle = findViewById<TextView>(R.id.tvReseniaDetalle)
        val im1 = findViewById<ImageView>(R.id.im1)
        val im2 = findViewById<ImageView>(R.id.im2)
        val im3 = findViewById<ImageView>(R.id.im3)

        createFragment()


        if (intent.extras != null){
            tvNombreDetalle.text = intent.getStringExtra("nombre").toString()
            tvAnioDetalle.text = intent.getStringExtra("anio").toString()
            tvCalifDetalle.text = intent.getStringExtra("calif").toString()
           tvCostoDetalle.text = intent.getStringExtra("costo").toString()
            tvReseniaDetalle.text = intent.getStringExtra("resenia").toString()

            nombre = intent.getStringExtra("nombre").toString()

            x = intent.getStringExtra("x").toString().toDouble()
            y = intent.getStringExtra("y").toString().toDouble()








            Picasso.get().load(intent.getStringExtra("foto1")).into(im1)
            Picasso.get().load(intent.getStringExtra("foto2")).into(im2)
            Picasso.get().load(intent.getStringExtra("foto3")).into(im3)
        }




    }

    private fun enableLocation(){
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }
    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        crearMarker()
        enableLocation()

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(x,y),17.0f))
    }
    private fun crearMarker(){
        val coordenadas = LatLng(x,y)
        val marker = MarkerOptions().position(coordenadas).title(nombre)
        map.addMarker(marker)
    }

    private fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }


    }

}