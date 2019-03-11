package com.constructor.sidisas.constructor.Models;

import android.icu.util.DateInterval;
import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by PC-1 on 19/2/2018.
 */

public class Utilidades {

    //Tabla actuaizaciones_db
    public static final String TABLA_ACTUALIZACION_DB="actualizacion_db";
    public static final String ACTUALIZACION_DB_ID="id";
    public static final String ACTUALIZACION_DB_SQL="abd_sqleje";
    public static final String ACTUALIZACION_DB_ESTADO="abd_estado";
    public static final String CREAR_TABLA_ACTUALIZACION_DB="CREATE TABLE "+TABLA_ACTUALIZACION_DB+" ("+ACTUALIZACION_DB_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ACTUALIZACION_DB_SQL+" TEXT,"+
            ACTUALIZACION_DB_ESTADO+" TEXT)";

    public static final String TABLA_AGENDAMIENTOS="agendamientos";
    public static final String AGENDAMIENTOS_ID="id";
    public static final String AGENDAMIENTOS_TAREA="age_htr_id";
    public static final String AGENDAMIENTOS_USUARIO_AGENDAMIENTO="age_usuage";
    public static final String AGENDAMIENTOS_USUARIO="age_usu_id";
    public static final String AGENDAMIENTOS_ESTADO="age_estado";
    public static final String AGENDAMIENTOS_CREATED_AT="created_at";
    public static final String AGENDAMIENTOS_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_AGENDAMIENTOS="CREATE TABLE "+TABLA_AGENDAMIENTOS+" ("+AGENDAMIENTOS_ID+" TEXT PRIMARY KEY,"+
            AGENDAMIENTOS_TAREA+" TEXT,"+
            AGENDAMIENTOS_USUARIO_AGENDAMIENTO+" TEXT,"+
            AGENDAMIENTOS_USUARIO+" TEXT,"+
            AGENDAMIENTOS_ESTADO+" TEXT,"+
            AGENDAMIENTOS_CREATED_AT+" TEXT,"+
            AGENDAMIENTOS_UPDATED_AT+" TEXT)";

    public static final String TABLA_AIEPI="aiepi";
    public static final String AIEPI_ID="id";
    public static final String AIEPI_PERSONA="aep_per_id";
    public static final String AIEPI_USUARIO="aep_usu_id";
    public static final String AIEPI_CREATED_AT="created_at";
    public static final String AIEPI_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_AIEPI="CREATE TABLE "+TABLA_AIEPI+" ("+AIEPI_ID+" TEXT PRIMARY KEY,"+
            AIEPI_PERSONA+" TEXT,"+
            AIEPI_USUARIO+" TEXT,"+
            AIEPI_CREATED_AT+" TEXT,"+
            AIEPI_UPDATED_AT+" TEXT)";

    public static final String TABLA_ARCHIVOS_ENFERMERIA="archivos_enfermeria";
    public static final String ARCHIVOS_ENFER_ID="id";
    public static final String ARCHIVOS_ENFER_ENFERMERIA="aef_enf_id";
    public static final String ARCHIVOS_ENFER_RUTA="aef_ruta";
    public static final String ARCHIVOS_ENFER_DESCRIPCION="aef_desc";
    public static final String ARCHIVOS_ENFER_USUARIO="aef_usu_id";
    public static final String ARCHIVOS_ENFER_CREATED_AT="created_at";
    public static final String ARCHIVOS_ENFER_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ARCHIVOS_ENFERMERIA="CREATE TABLE "+TABLA_ARCHIVOS_ENFERMERIA+" ("+ARCHIVOS_ENFER_ID+" TEXT PRIMARY KEY,"+
            ARCHIVOS_ENFER_ENFERMERIA+" TEXT,"+
            ARCHIVOS_ENFER_RUTA+" TEXT,"+
            ARCHIVOS_ENFER_DESCRIPCION+" TEXT,"+
            ARCHIVOS_ENFER_USUARIO+" TEXT,"+
            ARCHIVOS_ENFER_CREATED_AT+" TEXT,"+
            ARCHIVOS_ENFER_UPDATED_AT+" TEXT)";

    public static final String TABLA_ARCHIVOS_HOGAR="archivos_hogar";
    public static final String ARCHIVOS_HOGAR_ID="id";
    public static final String ARCHIVOS_HOGAR_HOGAR="afh_fch_id";
    public static final String ARCHIVOS_HOGAR_RUTA="afh_ruta";
    public static final String ARCHIVOS_HOGAR_DESCRIPCION="afh_desc";
    public static final String ARCHIVOS_HOGAR_USUARIO="afh_usu_id";
    public static final String ARCHIVOS_HOGAR_CREATED_AT="created_at";
    public static final String ARCHIVOS_HOGAR_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ARCHIVOS_HOGAR="CREATE TABLE "+TABLA_ARCHIVOS_HOGAR+" ("+ARCHIVOS_HOGAR_ID+" TEXT PRIMARY KEY,"+
            ARCHIVOS_HOGAR_HOGAR+" TEXT,"+
            ARCHIVOS_HOGAR_RUTA+" TEXT,"+
            ARCHIVOS_HOGAR_DESCRIPCION+" TEXT,"+
            ARCHIVOS_HOGAR_USUARIO+" TEXT,"+
            ARCHIVOS_HOGAR_CREATED_AT+" TEXT,"+
            ARCHIVOS_HOGAR_UPDATED_AT+" TEXT)";

    public static final String TABLA_ARCHIVOS_MEDICO="archivos_medico";
    public static final String ARCHIVOS_MEDICO_ID="id";
    public static final String ARCHIVOS_MEDICO_MEDICO="ame_med_id";
    public static final String ARCHIVOS_MEDICO_RUTA="ame_ruta";
    public static final String ARCHIVOS_MEDICO_DESCRIPCION="ame_desc";
    public static final String ARCHIVOS_MEDICO_USUARIO="ame_usu_id";
    public static final String ARCHIVOS_MEDICO_CREATED_AT="created_at";
    public static final String ARCHIVOS_MEDICO_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ARCHIVOS_MEDICO="CREATE TABLE "+TABLA_ARCHIVOS_MEDICO+" ("+ARCHIVOS_MEDICO_ID+" TEXT PRIMARY KEY,"+
            ARCHIVOS_MEDICO_MEDICO+" TEXT,"+
            ARCHIVOS_MEDICO_RUTA+" TEXT,"+
            ARCHIVOS_MEDICO_DESCRIPCION+" TEXT,"+
            ARCHIVOS_MEDICO_USUARIO+" TEXT,"+
            ARCHIVOS_MEDICO_CREATED_AT+" TEXT,"+
            ARCHIVOS_MEDICO_UPDATED_AT+" TEXT)";

    public static final String TABLA_ARCHIVOS_PSICOLOGIA="archivos_psicologia";
    public static final String ARCHIVOS_PSICOLOGIA_ID="id";
    public static final String ARCHIVOS_PSICOLOGIA_MEDICO="aps_psi_id";
    public static final String ARCHIVOS_PSICOLOGIA_RUTA="aps_ruta";
    public static final String ARCHIVOS_PSICOLOGIA_DESCRIPCION="aps_desc";
    public static final String ARCHIVOS_PSICOLOGIA_USUARIO="aps_usu_id";
    public static final String ARCHIVOS_PSICOLOGIA_CREATED_AT="created_at";
    public static final String ARCHIVOS_PSICOLOGIA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ARCHIVOS_PSICOLOGIA="CREATE TABLE "+TABLA_ARCHIVOS_PSICOLOGIA+" ("+ARCHIVOS_PSICOLOGIA_ID+" TEXT PRIMARY KEY,"+
            ARCHIVOS_PSICOLOGIA_MEDICO+" TEXT,"+
            ARCHIVOS_PSICOLOGIA_RUTA+" TEXT,"+
            ARCHIVOS_PSICOLOGIA_DESCRIPCION+" TEXT,"+
            ARCHIVOS_PSICOLOGIA_USUARIO+" TEXT,"+
            ARCHIVOS_PSICOLOGIA_CREATED_AT+" TEXT,"+
            ARCHIVOS_PSICOLOGIA_UPDATED_AT+" TEXT)";

    public static final String TABLA_ATENCION_CANALIZACION="atencion_canalizacion";
    public static final String ATENCION_CANALIZACION_ID="id";
    public static final String ATENCION_CANALIZACION_CANALIZACION="atc_id_canalizacion";
    public static final String ATENCION_CANALIZACION_FECHA_ATENCION="atc_fecha_atencion";
    public static final String ATENCION_CANALIZACION_DESCRIPCION="atc_des";
    public static final String ATENCION_CANALIZACION_USUARIO="atc_usu_id";
    public static final String ATENCION_CANALIZACION_CREATED_AT="created_at";
    public static final String ATENCION_CANALIZACION_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ATENCION_CANALIZACION="CREATE TABLE "+TABLA_ATENCION_CANALIZACION+" ("+ATENCION_CANALIZACION_ID+" TEXT PRIMARY KEY,"+
            ATENCION_CANALIZACION_CANALIZACION+" TEXT,"+
            ATENCION_CANALIZACION_FECHA_ATENCION+" TEXT,"+
            ATENCION_CANALIZACION_DESCRIPCION+" TEXT,"+
            ATENCION_CANALIZACION_USUARIO+" TEXT,"+
            ATENCION_CANALIZACION_CREATED_AT+" TEXT,"+
            ATENCION_CANALIZACION_UPDATED_AT+" TEXT)";

    //tabla barrios
    public static final String TABLA_BARRIOS="barrios";
    public static final String BARRIOS_ID="id";
    public static final String BARRIOS_NOMBRE="bar_nombre";
    public static final String BARRIOS_MUNICIPIO="bar_mun_id";
    public static final String BARRIOS_TIPO="bar_tipo";
    public static final String BARRIOS_COMUNA="bar_comuna";
    public static final String BARRIOS_COORDENADAS="bar_coorde";
    public static final String BARRIOS_USUARIO="bar_usu_id";
    public static final String BARRIOS_CREATED_AT="created_at";
    public static final String BARRIOS_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_BARRIOS="CREATE TABLE "+TABLA_BARRIOS+" ("+BARRIOS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            BARRIOS_NOMBRE+" TEXT,"+
            BARRIOS_MUNICIPIO+" INTEGER,"+
            BARRIOS_TIPO+" TEXT,"+
            BARRIOS_COMUNA+" TEXT,"+
            BARRIOS_COORDENADAS+" TEXT,"+
            BARRIOS_USUARIO+" TEXT,"+
            BARRIOS_CREATED_AT+" TEXT,"+
            BARRIOS_UPDATED_AT+" TEXT)";

    //tabla bloques
    public static final String TABLA_BLOQUE="bloques";
    public static final String BLOQUE_ID="id";
    public static final String BLOQUE_NOMBRE="blq_nombre";
    public static final String BLOQUE_FORMATO="blq_for_id";
    public static final String BLOQUE_ORDEN="blq_orden";
    public static final String BLOQUE_ESTADO="blq_estado";
    public static final String BLOQUE_CREATED_AT="created_at";
    public static final String CREAR_TABLA_BLOQUE="CREATE TABLE "+TABLA_BLOQUE+" ("+BLOQUE_ID+" TEXT PRIMARY KEY,"+
            BLOQUE_NOMBRE+" TEXT,"+
            BLOQUE_FORMATO+" INTEGER,"+
            BLOQUE_ORDEN+" INTEGER,"+
            BLOQUE_ESTADO+" TEXT,"+
            BLOQUE_CREATED_AT+" TEXT)";

    public static final String TABLA_CANALIZACION="canalizacion";
    public static final String CANALIZACION_ID="id";
    public static final String CANALIZACION_PERSONA="cnc_per_id";
    public static final String CANALIZACION_SERVICIO_REMITIDO="cnc_sret_id";
    public static final String CANALIZACION_USUARIO="cnc_usu_id";
    public static final String CANALIZACION_ESTADO="cnc_estado";
    public static final String CANALIZACION_RAZON_ANULADO="cnc_raz_anulado";
    public static final String CANALIZACION_CREATED_AT="created_at";
    public static final String CANALIZACION_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_CANALIZACION="CREATE TABLE "+TABLA_CANALIZACION+" ("+CANALIZACION_ID+" TEXT PRIMARY KEY,"+
            CANALIZACION_PERSONA+" TEXT,"+
            CANALIZACION_SERVICIO_REMITIDO+" TEXT,"+
            CANALIZACION_USUARIO+" TEXT,"+
            CANALIZACION_ESTADO+" TEXT,"+
            CANALIZACION_RAZON_ANULADO+" TEXT,"+
            CANALIZACION_CREATED_AT+" TEXT,"+
            CANALIZACION_UPDATED_AT+" TEXT)";

    public static final String TABLA_CANCER_MAMA="cancer_mama";
    public static final String CANCER_MAMA_ID="id";
    public static final String CANCER_MAMA_PERSONA="cac_per_id";
    public static final String CANCER_MAMA_USUARIO="cac_usu_id";
    public static final String CANCER_MAMA_CREATED_AT="created_at";
    public static final String CANCER_MAMA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_CANCER_MAMA="CREATE TABLE "+TABLA_CANCER_MAMA+" ("+CANCER_MAMA_ID+" TEXT PRIMARY KEY,"+
            CANCER_MAMA_PERSONA+" TEXT,"+
            CANCER_MAMA_USUARIO+" TEXT,"+
            CANCER_MAMA_CREATED_AT+" TEXT,"+
            CANCER_MAMA_UPDATED_AT+" TEXT)";

    public static final String TABLA_CIE10="cie10";
    public static final String CIE10_ID="id";
    public static final String CIE10_CODIGO="cie_codigo";
    public static final String CIE10_DESCRIPCION="cie_descrip";
    public static final String CREAR_TABLA_CIE10="CREATE TABLE "+TABLA_CIE10+" ("+CIE10_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            CIE10_CODIGO+" TEXT,"+
            CIE10_DESCRIPCION+" TEXT)";

    public static final String TABLA_CONFIGURACIONES="configuraciones";
    public static final String CONFIGURACIONES_ID="id";
    public static final String CONFIGURACIONES_HORA_DESCARGA="cfg_hrdesc";
    public static final String CONFIGURACIONES_SEMANAS_PENDIENTES="cfg_sempen";
    public static final String CONFIGURACIONES_USUARIO="cfg_usu_id";
    public static final String CONFIGURACIONES_ENTIDAD="cfg_ent_id";
    public static final String CONFIGURACIONES_ESTADO="cfg_estado";
    public static final String CONFIGURACIONES_CREATED_AT="created_at";
    public static final String CONFIGURACIONES_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_CONFIGURACIONES="CREATE TABLE "+TABLA_CONFIGURACIONES+" ("+CONFIGURACIONES_ID+" TEXT PRIMARY KEY,"+
            CONFIGURACIONES_HORA_DESCARGA+" TEXT,"+
            CONFIGURACIONES_SEMANAS_PENDIENTES+" TEXT,"+
            CONFIGURACIONES_USUARIO+" TEXT,"+
            CONFIGURACIONES_ENTIDAD+" TEXT,"+
            CONFIGURACIONES_ESTADO+" TEXT,"+
            CONFIGURACIONES_CREATED_AT+" TEXT,"+
            CONFIGURACIONES_UPDATED_AT+" TEXT)";

    public static final String TABLA_DEMANDA_INDUCIDA="demanda_inducida";
    public static final String DEMANDA_INDUCIDA_ID="id";
    public static final String DEMANDA_INDUCIDA_PERSONA="dmi_per_id";
    public static final String DEMANDA_INDUCIDA_USUARIO="dmi_usu_id";
    public static final String DEMANDA_INDUCIDA_CREATED_AT="created_at";
    public static final String DEMANDA_INDUCIDA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_DEMANDA_INDUCIDA="CREATE TABLE "+TABLA_DEMANDA_INDUCIDA+" ("+DEMANDA_INDUCIDA_ID+" TEXT PRIMARY KEY,"+
            DEMANDA_INDUCIDA_PERSONA+" TEXT,"+
            DEMANDA_INDUCIDA_USUARIO+" TEXT,"+
            DEMANDA_INDUCIDA_CREATED_AT+" TEXT,"+
            DEMANDA_INDUCIDA_UPDATED_AT+" TEXT)";

    //Tabla departamento
    public static final String TABLA_DEPARTAMENTO="departamento";
    public static final String DEPARTAMENTO_ID="id";
    public static final String DEPARTAMENTO_CODIGO="dep_codigo";
    public static final String DEPARTAMENTO_NOMBRE="dep_nombre";
    public static final String CREAR_TABLA_DEPARTAMENTO="CREATE TABLE "+TABLA_DEPARTAMENTO+" ("+DEPARTAMENTO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            DEPARTAMENTO_CODIGO+" TEXT,"+
            DEPARTAMENTO_NOMBRE+" TEXT)";

    public static final String TABLA_DIAGNOSTICO_MEDICINA="diagnostico_medicina";
    public static final String DIAGNOSTICO_MEDICINA_ID="id";
    public static final String DIAGNOSTICO_MEDICINA_MEDICINA="dgm_med_id";
    public static final String DIAGNOSTICO_MEDICINA_CIE10="dgm_cie_id";
    public static final String DIAGNOSTICO_MEDICINA_USUARIO="dgm_usu_id";
    public static final String DIAGNOSTICO_MEDICINA_CREATED_AT="created_at";
    public static final String DIAGNOSTICO_MEDICINA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_DIAGNOSTICO_MEDICINA="CREATE TABLE "+TABLA_DIAGNOSTICO_MEDICINA+" ("+DIAGNOSTICO_MEDICINA_ID +" TEXT PRIMARY KEY,"+
            DIAGNOSTICO_MEDICINA_MEDICINA+" TEXT,"+
            DIAGNOSTICO_MEDICINA_CIE10+" TEXT,"+
            DIAGNOSTICO_MEDICINA_USUARIO+" TEXT,"+
            DIAGNOSTICO_MEDICINA_CREATED_AT+" TEXT,"+
            DIAGNOSTICO_MEDICINA_UPDATED_AT+" TEXT)";

    public static final String TABLA_ENFERMERIA="enfermeria";
    public static final String ENFERMERIA_ID="id";
    public static final String ENFERMERIA_PERSONA="enf_per_id";
    public static final String ENFERMERIA_USUARIO="enf_usu_id";
    public static final String ENFERMERIA_CREATED_AT="created_at";
    public static final String ENFERMERIA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ENFERMERIA="CREATE TABLE "+TABLA_ENFERMERIA+" ("+ENFERMERIA_ID+" TEXT PRIMARY KEY,"+
            ENFERMERIA_PERSONA+" TEXT,"+
            ENFERMERIA_USUARIO+" TEXT,"+
            ENFERMERIA_CREATED_AT+" TEXT,"+
            ENFERMERIA_UPDATED_AT+" TEXT)";

    //tabla entidad
    public static final String TABLA_ENTIDAD="entidad";
    public static final String ENTIDAD_ID="id";
    public static final String ENTIDAD_MUNICIPIO="ent_mun_id";
    public static final String ENTIDAD_NOMBRE="ent_nombre";
    public static final String ENTIDAD_USUARIO="ent_usu_id";
    public static final String ENTIDAD_CREATED_AT="created_at";
    public static final String ENTIDAD_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_ENTIDAD="CREATE TABLE "+TABLA_ENTIDAD+" ("+ENTIDAD_ID+" TEXT PRIMARY KEY,"+
            ENTIDAD_MUNICIPIO+" INTEGER,"+
            ENTIDAD_NOMBRE+" TEXT,"+
            ENTIDAD_USUARIO+" TEXT,"+
            ENTIDAD_CREATED_AT+" TEXT,"+
            ENTIDAD_UPDATED_AT+" TEXT)";

    //Tabla eps
    public static final String TABLA_EPS="eps";
    public static final String EPS_ID="id";
    public static final String EPS_NOMBRE="eps_nombre";
    public static final String EPS_USUARIO="eps_usu_id";
    public static final String EPS_CREATED_AT="created_at";
    public static final String EPS_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_EPS="CREATE TABLE "+TABLA_EPS+" ("+EPS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EPS_NOMBRE+" TEXT,"+
            EPS_USUARIO+" TEXT,"+
            EPS_CREATED_AT+" TEXT,"+
            EPS_UPDATED_AT+" TEXT)";

    public static final String TABLA_FICHA_HOGAR="ficha_hogar";
    public static final String FICHA_HOGAR_ID="id";
    public static final String FICHA_HOGAR_CODIGO="fch_codigo";
    public static final String FICHA_HOGAR_NOMECLATURA="fch_nomecl";
    public static final String FICHA_HOGAR_ZONA="fch_zona";
    public static final String FICHA_HOGAR_MUNICIPIO="fch_mun_id";
    public static final String FICHA_HOGAR_BARRIO="fch_bar_id";
    public static final String FICHA_HOGAR_FIRMA="fch_firma";
    public static final String FICHA_HOGAR_DOCFIRMA="fch_docfrm";
    public static final String FICHA_HOGAR_CLASIFICADO="fch_clasif";
    public static final String FICHA_HOGAR_COORDENADAS="fch_coordenadas";
    public static final String FICHA_HOGAR_USUARIO="fch_usu_id";
    public static final String FICHA_HOGAR_CREATED_AT="created_at";
    public static final String FICHA_HOGAR_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_FICHA_HOGAR="CREATE TABLE "+TABLA_FICHA_HOGAR+" ("+FICHA_HOGAR_ID+" TEXT PRIMARY KEY,"+
            FICHA_HOGAR_CODIGO+" TEXT,"+
            FICHA_HOGAR_NOMECLATURA+" TEXT,"+
            FICHA_HOGAR_ZONA+" TEXT,"+
            FICHA_HOGAR_MUNICIPIO+" INTEGER,"+
            FICHA_HOGAR_BARRIO+" INTEGER,"+
            FICHA_HOGAR_FIRMA+" TEXT,"+
            FICHA_HOGAR_DOCFIRMA+" TEXT,"+
            FICHA_HOGAR_CLASIFICADO+" TEXT,"+
            FICHA_HOGAR_COORDENADAS+" TEXT,"+
            FICHA_HOGAR_USUARIO+" TEXT,"+
            FICHA_HOGAR_CREATED_AT+" TEXT,"+
            FICHA_HOGAR_UPDATED_AT+" TEXT)";

    //tabla formato
    public static final String TABLA_FORMATO="formato";
    public static final String FORMATO_ID="id";
    public static final String FORMATO_NOMBRE="for_nombre";
    public static final String CREAR_TABLA_FORMATO="CREATE TABLE "+TABLA_FORMATO+" ("+FORMATO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            FORMATO_NOMBRE+" TEXT)";

    public static final String TABLA_HOJA_TRABAJO="hoja_trabajo";
    public static final String HOJA_TRABAJO_ID="id";
    public static final String HOJA_TRABAJO_PERSONA="htr_per_id";
    public static final String HOJA_TRABAJO_USUARIO="htr_usu_id";
    public static final String HOJA_TRABAJO_FORMATO="htr_for_id";
    public static final String HOJA_TRABAJO_ESTADO="htr_estado";
    public static final String HOJA_TRABAJO_CREATED_AT="created_at";
    public static final String HOJA_TRABAJO_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_HOJA_TRABAJO="CREATE TABLE "+TABLA_HOJA_TRABAJO+" ("+HOJA_TRABAJO_ID+" TEXT PRIMARY KEY,"+
            HOJA_TRABAJO_PERSONA+" TEXT,"+
            HOJA_TRABAJO_USUARIO+" TEXT,"+
            HOJA_TRABAJO_FORMATO+" TEXT,"+
            HOJA_TRABAJO_ESTADO+" TEXT,"+
            HOJA_TRABAJO_CREATED_AT+" TEXT,"+
            HOJA_TRABAJO_UPDATED_AT+" TEXT)";

    public static final String TABLA_KARDEX="kardex";
    public static final String KARDEX_ID="id";
    public static final String KARDEX_PERSONA="kar_per_id";
    public static final String KARDEX_USUARIO="kar_usu_id";
    public static final String KARDEX_CREATED_AT="created_at";
    public static final String KARDEX_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_KARDEX="CREATE TABLE "+TABLA_KARDEX+" ("+KARDEX_ID+" TEXT PRIMARY KEY,"+
            KARDEX_PERSONA+" TEXT,"+
            KARDEX_USUARIO+" TEXT,"+
            KARDEX_CREATED_AT+" TEXT,"+
            KARDEX_UPDATED_AT+" TEXT)";

    public static final String TABLA_MEDICINA="medicina";
    public static final String MEDICINA_ID="id";
    public static final String MEDICINA_PERSONA="med_per_id";
    public static final String MEDICINA_USUARIO="med_usu_id";
    public static final String MEDICINA_CREATED_AT="created_at";
    public static final String MEDICINA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_MEDICINA="CREATE TABLE "+TABLA_MEDICINA+" ("+MEDICINA_ID+" TEXT PRIMARY KEY,"+
            MEDICINA_PERSONA+" TEXT,"+
            MEDICINA_USUARIO+" TEXT,"+
            MEDICINA_CREATED_AT+" TEXT,"+
            MEDICINA_UPDATED_AT+" TEXT)";

    //tabla municipios
    public static final String TABLA_MUNICIPIO="municipio";
    public static final String MUNICIPIO_ID="id";
    public static final String MUNICIPIO_CODIGO="mun_codigo";
    public static final String MUNICIPIO_NOMBRE="mun_nombre";
    public static final String MUNICIPIO_DEPARTAMENTO="mun_depart";
    public static final String MUNICIPIO_PROVINCIA="mun_provin";
    public static final String MUNICIPIO_USUARIO="mun_usu_id";
    public static final String MUNICIPIO_CREATED_AT="created_at";
    public static final String MUNICIPIO_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_MUNICIPIO="CREATE TABLE "+TABLA_MUNICIPIO+" ("+MUNICIPIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            MUNICIPIO_CODIGO+" TEXT,"+
            MUNICIPIO_NOMBRE+" TEXT,"+
            MUNICIPIO_DEPARTAMENTO+" INTEGER,"+
            MUNICIPIO_PROVINCIA+" INTEGER,"+
            MUNICIPIO_USUARIO+" TEXT,"+
            MUNICIPIO_CREATED_AT+" TEXT,"+
            MUNICIPIO_UPDATED_AT+" TEXT)";

    public static final String TABLA_OBSERVACIONES="observaciones";
    public static final String OBSERVACIONES_ID="id";
    public static final String OBSERVACIONES_HOJA_TRABAJO="obs_htr_id";
    public static final String OBSERVACIONES_OBSERVACION="obs_observ";
    public static final String OBSERVACIONES_USUARIO="obs_usu_id";
    public static final String OBSERVACIONES_CREATED_AT="created_at";
    public static final String OBSERVACIONES_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_OBSERVACIONES="CREATE TABLE "+TABLA_OBSERVACIONES+" ("+OBSERVACIONES_ID+" TEXT PRIMARY KEY,"+
            OBSERVACIONES_HOJA_TRABAJO+" TEXT,"+
            OBSERVACIONES_OBSERVACION+" TEXT,"+
            OBSERVACIONES_USUARIO+" TEXT,"+
            OBSERVACIONES_CREATED_AT+" TEXT,"+
            OBSERVACIONES_UPDATED_AT+" TEXT)";

    public static final String TABLA_PASSWORD_RESET="password_resets";
    public static final String PASSWORD_RESET_EMAIL="email";
    public static final String PASSWORD_RESET_TOKEN="token";
    public static final String PASSWORD_RESET_CREATED_AT="created_at";
    public static final String CREAR_TABLA_PASSWROD_RESET="CREATE TABLE "+TABLA_PASSWORD_RESET+" ("+PASSWORD_RESET_EMAIL+" TEXT,"+
            PASSWORD_RESET_TOKEN+" TEXT,"+
            PASSWORD_RESET_CREATED_AT+" TEXT)";

    public static final String TABLA_PERMISOS="permisos";
    public static final String PERMISOS_ID="id";
    public static final String PERMISOS_DESCRIPCION="desc_permiso";
    public static final String CREAR_TABLA_PERMISOS="CREATE TABLE "+TABLA_PERMISOS+" ("+PERMISOS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PERMISOS_DESCRIPCION+" TEXT)";

    public static final String TABLA_PERMISOS_USUARIO="permisos_usuario";
    public static final String PERMISOS_USUARIO_ID="id";
    public static final String PERMISOS_USUARIO_USUARIO="pus_usu_id";
    public static final String PERMISOS_USUARIO_PERMISO="pus_perm_id";
    public static final String PERMISOS_USUARIO_CREATED_AT="created_at";
    public static final String PERMISOS_USUARIO_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_PERMISOS_USUARIO="CREATE TABLE "+TABLA_PERMISOS_USUARIO+" ("+PERMISOS_USUARIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PERMISOS_USUARIO_USUARIO+" TEXT,"+
            PERMISOS_USUARIO_PERMISO+" TEXT,"+
            PERMISOS_USUARIO_CREATED_AT+" TEXT,"+
            PERMISOS_USUARIO_UPDATED_AT+" TEXT)";

    public static final String TABLA_PERSONAS="personas";
    public static final String PERSONAS_ID="id";
    public static final String PERSONAS_CODIGO="per_codigo";
    public static final String PERSONAS_FICHA="per_fch_id";
    public static final String PERSONAS_TIPO_DOCUMENTO="per_tpd_id";
    public static final String PERSONAS_IDENTIFICACION="per_identi";
    public static final String PERSONAS_NOMBRE="per_nombre";
    public static final String PERSONAS_APELLIDO="per_apelli";
    public static final String PERSONAS_FECHA_NACIMIENTO="per_fchnac";
    public static final String PERSONAS_EPS="per_eps_id";
    public static final String PERSONAS_EDAD="per_edad";
    public static final String PERSONAS_GENERO="per_genero";
    public static final String PERSONAS_USUARIO="per_usu_id";
    public static final String PERSONAS_CREATED_AT="created_at";
    public static final String PERSONAS_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_PERSONAS="CREATE TABLE "+TABLA_PERSONAS+" ("+PERSONAS_ID+" TEXT PRIMARY KEY,"+
            PERSONAS_CODIGO+" TEXT,"+
            PERSONAS_FICHA+" TEXT,"+
            PERSONAS_TIPO_DOCUMENTO+" TEXT,"+
            PERSONAS_IDENTIFICACION+" TEXT,"+
            PERSONAS_NOMBRE+" TEXT,"+
            PERSONAS_APELLIDO+" TEXT,"+
            PERSONAS_FECHA_NACIMIENTO+" TEXT,"+
            PERSONAS_EPS+" TEXT,"+
            PERSONAS_EDAD+" TEXT,"+
            PERSONAS_GENERO+" TEXT,"+
            PERSONAS_USUARIO+" TEXT,"+
            PERSONAS_CREATED_AT+" TEXT,"+
            PERSONAS_UPDATED_AT+" TEXT)";

    //tabla preguntas
    public static final String TABLA_PREGUNTA="preguntas";
    public static final String PREGUNTA_ID="id";
    public static final String PREGUNTA_NOMBRE="prg_descri";
    public static final String PREGUNTA_TIPO="prg_tipo";
    public static final String PREGUNTA_VALID="prg_valid";
    public static final String PREGUNTA_BLOQUE="prg_blq_id";
    public static final String PREGUNTA_LONGITUD="prg_longitud";
    public static final String PREGUNTA_ORDEN="prg_orden";
    public static final String PREGUNTA_ESTADO="prg_estado";
    public static final String PREGUNTA_USUARIO="pgr_usu_id";
    public static final String PREGUNTA_CREATED_AT="created_at";
    public static final String CREAR_TABLA_PREGUNTAS="CREATE TABLE "+TABLA_PREGUNTA+" ("+PREGUNTA_ID+" TEXT PRIMARY KEY,"+
            PREGUNTA_NOMBRE+" TEXT,"+
            PREGUNTA_TIPO+" TEXT,"+
            PREGUNTA_VALID+" TEXT,"+
            PREGUNTA_BLOQUE+" TEXT,"+
            PREGUNTA_LONGITUD+" INTEGER,"+
            PREGUNTA_ORDEN+" INTEGER,"+
            PREGUNTA_ESTADO+" TEXT,"+
            PREGUNTA_USUARIO+" TEXT,"+
            PREGUNTA_CREATED_AT+" TEXT)";

    public static final String TABLA_PROMOCION="promocion";
    public static final String PROMOCION_ID="id";
    public static final String PROMOCION_PERSONA="prm_per_id";
    public static final String PROMOCION_USUARIO_ID="prm_usu_id";
    public static final String PROMOCION_CREATED_AT="created_at";
    public static final String PROMOCION_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_PROMOCION="CREATE TABLE "+TABLA_PROMOCION+" ("+PROMOCION_ID+" TEXT PRIMARY KEY,"+
            PROMOCION_PERSONA+" TEXT,"+
            PROMOCION_USUARIO_ID+" TEXT,"+
            PROMOCION_CREATED_AT+" TEXT,"+
            PROMOCION_UPDATED_AT+" TEXT)";

    //tabla provincias
    public static final String TABLA_PROVINCIAS="provincia";
    public static final String PROVINCIAS_ID="id";
    public static final String PROVINCIAS_NOMBRE="pro_nombre";
    public static final String PROVINCIAS_USUARIO="pro_usu_id";
    public static final String PROVINCIAS_CREATED_AT="pro_fecreg";
    public static final String CREAR_TABLA_PROVINCIAS="CREATE TABLE "+TABLA_PROVINCIAS+" ("+PROVINCIAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PROVINCIAS_NOMBRE+" TEXT,"+
            PROVINCIAS_USUARIO+" TEXT,"+
            PROVINCIAS_CREATED_AT+" TEXT)";

    public static final String TABLA_PSICOLOGIA="psicologia";
    public static final String PSICOLOGIA_ID="id";
    public static final String PSICOLOGIA_PERSONA="psi_per_id";
    public static final String PSICOLOGIA_USUARIO_ID="psi_usu_id";
    public static final String PSICOLOGIA_CREATED_AT="created_at";
    public static final String PSICOLOGIA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_PSICOLOGIA="CREATE TABLE "+TABLA_PSICOLOGIA+" ("+PSICOLOGIA_ID+" TEXT PRIMARY KEY,"+
            PSICOLOGIA_PERSONA+" TEXT,"+
            PSICOLOGIA_USUARIO_ID+" TEXT,"+
            PSICOLOGIA_CREATED_AT+" TEXT,"+
            PSICOLOGIA_UPDATED_AT+" TEXT)";

    //tabla respuestas
    public static final String TABLA_RESPUESTAS="respuestas";
    public static final String RESPUESTA_ID="id";
    public static final String RESPUESTA_NOMBRE="rep_descri";
    public static final String RESPUESTA_PREGUNTA="rep_prg_id";
    public static final String RESPUESTA_FORMATO_REDIRECCION="rep_for_id";
    public static final String RESPUESTA_ESTADO="rep_estado";
    public static final String RESPUESTA_CREATED_AT="created_at";
    public static final String CREAR_TABLA_RESPUESTAS="CREATE TABLE "+TABLA_RESPUESTAS+" ("+RESPUESTA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTA_NOMBRE+" TEXT,"+
            RESPUESTA_PREGUNTA+" TEXT,"+
            RESPUESTA_FORMATO_REDIRECCION+" INTEGER,"+
            RESPUESTA_ESTADO+" TEXT,"+
            RESPUESTA_CREATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_AIEPI="respuestas_aiepi";
    public static final String RESPUESTAS_AIEPI_ID="id";
    public static final String RESPUESTAS_AIEPI_AIEPI="rai_aep_id";
    public static final String RESPUESTAS_AIEPI_RESPUESTA="rai_rep_id";
    public static final String RESPUESTAS_AIEPI_DESCRIPCION="rai_descri";
    public static final String RESPUESTAS_AIEPI_USUARIO="rai_usu_id";
    public static final String RESPUESTAS_AIEPI_CREATED_AT="created_at";
    public static final String RESPUESTAS_AIEPI_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_AIEPI="CREATE TABLE "+TABLA_RESPUESTAS_AIEPI+" ("+RESPUESTAS_AIEPI_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_AIEPI_AIEPI+" TEXT,"+
            RESPUESTAS_AIEPI_RESPUESTA+" TEXT,"+
            RESPUESTAS_AIEPI_DESCRIPCION+" TEXT,"+
            RESPUESTAS_AIEPI_USUARIO+" TEXT,"+
            RESPUESTAS_AIEPI_CREATED_AT+" TEXT,"+
            RESPUESTAS_AIEPI_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_CANALIZACION="respuestas_canalizacion";
    public static final String RESPUESTAS_CANALIZACION_ID="id";
    public static final String RESPUESTAS_CANALIZACION_ATENCION_CANALIZACION="id_atc_canalizacion";
    public static final String RESPUESTAS_CANALIZACION_DIAGNOSTICO="id_diagnostico";
    public static final String RESPUESTAS_CANALIZACION_FECHA_ATENCION="fecha_atencion";
    public static final String RESPUESTAS_CANALIZACION_DESCRIPCION="descripcion_atencion";
    public static final String RESPUESTAS_CANALIZACION_USUARIO="usu_id";
    public static final String RESPUESTAS_CANALIZACION_CREATED_AT="created_at";
    public static final String RESPUESTAS_CANALIZACION_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_CANALIZACION="CREATE TABLE "+TABLA_RESPUESTAS_CANALIZACION+" ("+RESPUESTAS_CANALIZACION_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_CANALIZACION_ATENCION_CANALIZACION+" TEXT,"+
            RESPUESTAS_CANALIZACION_DIAGNOSTICO+" TEXT,"+
            RESPUESTAS_CANALIZACION_FECHA_ATENCION+" TEXT,"+
            RESPUESTAS_CANALIZACION_DESCRIPCION+" TEXT,"+
            RESPUESTAS_CANALIZACION_USUARIO+" TEXT,"+
            RESPUESTAS_CANALIZACION_CREATED_AT+" TEXT,"+
            RESPUESTAS_CANALIZACION_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_CANCER_MAMA="respuestas_cancer_mama";
    public static final String RESPUESTAS_CANCER_MAMA_ID="id";
    public static final String RESPUESTAS_CANCER_MAMA_CANCER_MAMA="rcm_cac_id";
    public static final String RESPUESTAS_CANCER_MAMA_RESPUESTA="rcm_rep_id";
    public static final String RESPUESTAS_CANCER_MAMA_DESCRIPCION="rcm_descri";
    public static final String RESPUESTAS_CANCER_MAMA_USUARIO="rcm_usu_id";
    public static final String RESPUESTAS_CANCER_MAMA_CREATED_AT="created_at";
    public static final String RESPUESTAS_CANCER_MAMA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_CANCER_MAMA="CREATE TABLE "+TABLA_RESPUESTAS_CANCER_MAMA+" ("+RESPUESTAS_CANCER_MAMA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_CANCER_MAMA_CANCER_MAMA+" TEXT,"+
            RESPUESTAS_CANCER_MAMA_RESPUESTA+" TEXT,"+
            RESPUESTAS_CANCER_MAMA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_CANCER_MAMA_USUARIO+" TEXT,"+
            RESPUESTAS_CANCER_MAMA_CREATED_AT+" TEXT,"+
            RESPUESTAS_CANCER_MAMA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_DEMANDA_INDUCIDA="respuestas_demanda_inducida";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_ID="id";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_DEMANDA_INDUCIDA="rdm_dmi_id";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_RESPUESTA="rdm_rep_id";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_DESCRIPCION="rdm_descri";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_USUARIO="rdm_usu_id";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_CREATED_AT="created_at";
    public static final String RESPUESTAS_DEMANDA_INDUCIDA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_DEMANDA_INDUCIDA="CREATE TABLE "+TABLA_RESPUESTAS_DEMANDA_INDUCIDA+" ("+RESPUESTAS_DEMANDA_INDUCIDA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_DEMANDA_INDUCIDA_DEMANDA_INDUCIDA+" TEXT,"+
            RESPUESTAS_DEMANDA_INDUCIDA_RESPUESTA+" TEXT,"+
            RESPUESTAS_DEMANDA_INDUCIDA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_DEMANDA_INDUCIDA_USUARIO+" TEXT,"+
            RESPUESTAS_DEMANDA_INDUCIDA_CREATED_AT+" TEXT,"+
            RESPUESTAS_DEMANDA_INDUCIDA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_ENFERMERIA="respuestas_enfermeria";
    public static final String RESPUESTAS_ENFERMERIA_ID="id";
    public static final String RESPUESTAS_ENFERMERIA_ENFERMERIA="ref_enf_id";
    public static final String RESPUESTAS_ENFERMERIA_RESPUESTA="ref_rep_id";
    public static final String RESPUESTAS_ENFERMERIA_DESCRIPCION="ref_descri";
    public static final String RESPUESTAS_ENFERMERIA_USUARIO="ref_usu_id";
    public static final String RESPUESTAS_ENFERMERIA_CREATED_AT="created_at";
    public static final String RESPUESTAS_ENFERMERIA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_ENFERMERIA="CREATE TABLE "+TABLA_RESPUESTAS_ENFERMERIA+" ("+RESPUESTAS_ENFERMERIA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_ENFERMERIA_ENFERMERIA+" TEXT,"+
            RESPUESTAS_ENFERMERIA_RESPUESTA+" TEXT,"+
            RESPUESTAS_ENFERMERIA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_ENFERMERIA_USUARIO+" TEXT,"+
            RESPUESTAS_ENFERMERIA_CREATED_AT+" TEXT,"+
            RESPUESTAS_ENFERMERIA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_HOGAR="respuestas_hogar";
    public static final String RESPUESTAS_HOGAR_ID="id";
    public static final String RESPUESTAS_HOGAR_HOGAR="rph_fch_id";
    public static final String RESPUESTAS_HOGAR_RESPUESTA="rph_rep_id";
    public static final String RESPUESTAS_HOGAR_DESCRIPCION="rph_observ";
    public static final String RESPUESTAS_HOGAR_USUARIO="rph_usu_id";
    public static final String RESPUESTAS_HOGAR_CREATED_AT="created_at";
    public static final String RESPUESTAS_HOGAR_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_HOGAR="CREATE TABLE "+TABLA_RESPUESTAS_HOGAR+" ("+RESPUESTAS_HOGAR_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_HOGAR_HOGAR+" TEXT,"+
            RESPUESTAS_HOGAR_RESPUESTA+" TEXT,"+
            RESPUESTAS_HOGAR_DESCRIPCION+" TEXT,"+
            RESPUESTAS_HOGAR_USUARIO+" TEXT,"+
            RESPUESTAS_HOGAR_CREATED_AT+" TEXT,"+
            RESPUESTAS_HOGAR_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_KARDEX="respuestas_kardex";
    public static final String RESPUESTAS_KARDEX_ID="id";
    public static final String RESPUESTAS_KARDEX_KARDEX="rpk_kar_id";
    public static final String RESPUESTAS_KARDEX_RESPUESTA="rpk_rep_id";
    public static final String RESPUESTAS_KARDEX_DESCRIPCION="rpk_descri";
    public static final String RESPUESTAS_KARDEX_USUARIO="rpk_usu_id";
    public static final String RESPUESTAS_KARDEX_CREATED_AT="created_at";
    public static final String RESPUESTAS_KARDEX_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_KARDEX="CREATE TABLE "+TABLA_RESPUESTAS_KARDEX+" ("+RESPUESTAS_KARDEX_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_KARDEX_KARDEX+" TEXT,"+
            RESPUESTAS_KARDEX_RESPUESTA+" TEXT,"+
            RESPUESTAS_KARDEX_DESCRIPCION+" TEXT,"+
            RESPUESTAS_KARDEX_USUARIO+" TEXT,"+
            RESPUESTAS_KARDEX_CREATED_AT+" TEXT,"+
            RESPUESTAS_KARDEX_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_MEDICINA="respuestas_medicina";
    public static final String RESPUESTAS_MEDICINA_ID="id";
    public static final String RESPUESTAS_MEDICINA_MEDICINA="rmd_med_id";
    public static final String RESPUESTAS_MEDICINA_RESPUESTA="rmd_rep_id";
    public static final String RESPUESTAS_MEDICINA_DESCRIPCION="rmd_descri";
    public static final String RESPUESTAS_MEDICINA_USUARIO="rmd_usu_id";
    public static final String RESPUESTAS_MEDICINA_CREATED_AT="created_at";
    public static final String RESPUESTAS_MEDICINA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_MEDICINA="CREATE TABLE "+TABLA_RESPUESTAS_MEDICINA+" ("+RESPUESTAS_MEDICINA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_MEDICINA_MEDICINA+" TEXT,"+
            RESPUESTAS_MEDICINA_RESPUESTA+" TEXT,"+
            RESPUESTAS_MEDICINA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_MEDICINA_USUARIO+" TEXT,"+
            RESPUESTAS_MEDICINA_CREATED_AT+" TEXT,"+
            RESPUESTAS_MEDICINA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_PERSONA="respuestas_persona";
    public static final String RESPUESTAS_PERSONA_ID="id";
    public static final String RESPUESTAS_PERSONA_PERSONA="rpp_per_id";
    public static final String RESPUESTAS_PERSONA_RESPUESTA="rpp_rep_id";
    public static final String RESPUESTAS_PERSONA_DESCRIPCION="rpp_observ";
    public static final String RESPUESTAS_PERSONA_USUARIO="rpp_usu_id";
    public static final String RESPUESTAS_PERSONA_CREATED_AT="created_at";
    public static final String RESPUESTAS_PERSONA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_PERSONA="CREATE TABLE "+TABLA_RESPUESTAS_PERSONA+" ("+RESPUESTAS_PERSONA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_PERSONA_PERSONA+" TEXT,"+
            RESPUESTAS_PERSONA_RESPUESTA+" TEXT,"+
            RESPUESTAS_PERSONA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_PERSONA_USUARIO+" TEXT,"+
            RESPUESTAS_PERSONA_CREATED_AT+" TEXT,"+
            RESPUESTAS_PERSONA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_PROMOCION="respuestas_promocion";
    public static final String RESPUESTAS_PROMOCION_ID="id";
    public static final String RESPUESTAS_PROMOCION_PROMOCION="rpm_prm_id";
    public static final String RESPUESTAS_PROMOCION_RESPUESTA="rpm_rep_id";
    public static final String RESPUESTAS_PROMOCION_DESCRIPCION="rpm_descri";
    public static final String RESPUESTAS_PROMOCION_USUARIO="rpm_usu_id";
    public static final String RESPUESTAS_PROMOCION_CREATED_AT="created_at";
    public static final String RESPUESTAS_PROMOCION_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_PROMOCION="CREATE TABLE "+TABLA_RESPUESTAS_PROMOCION+" ("+RESPUESTAS_PROMOCION_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_PROMOCION_PROMOCION+" TEXT,"+
            RESPUESTAS_PROMOCION_RESPUESTA+" TEXT,"+
            RESPUESTAS_PROMOCION_DESCRIPCION+" TEXT,"+
            RESPUESTAS_PROMOCION_USUARIO+" TEXT,"+
            RESPUESTAS_PROMOCION_CREATED_AT+" TEXT,"+
            RESPUESTAS_PROMOCION_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_PSICOLOGIA="respuestas_psicologia";
    public static final String RESPUESTAS_PSICOLOGIA_ID="id";
    public static final String RESPUESTAS_PSICOLOGIA_PSICOLOGIA="rps_psi_id";
    public static final String RESPUESTAS_PSICOLOGIA_RESPUESTA="rps_rep_id";
    public static final String RESPUESTAS_PSICOLOGIA_DESCRIPCION="rps_descri";
    public static final String RESPUESTAS_PSICOLOGIA_USUARIO="rps_usu_id";
    public static final String RESPUESTAS_PSICOLOGIA_CREATED_AT="created_at";
    public static final String RESPUESTAS_PSICOLOGIA_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_PSICOLOGIA="CREATE TABLE "+TABLA_RESPUESTAS_PSICOLOGIA+" ("+RESPUESTAS_PSICOLOGIA_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_PSICOLOGIA_PSICOLOGIA+" TEXT,"+
            RESPUESTAS_PSICOLOGIA_RESPUESTA+" TEXT,"+
            RESPUESTAS_PSICOLOGIA_DESCRIPCION+" TEXT,"+
            RESPUESTAS_PSICOLOGIA_USUARIO+" TEXT,"+
            RESPUESTAS_PSICOLOGIA_CREATED_AT+" TEXT,"+
            RESPUESTAS_PSICOLOGIA_UPDATED_AT+" TEXT)";

    public static final String TABLA_RESPUESTAS_TEST_FINDRISK="respuestas_test_findrisk";
    public static final String RESPUESTAS_TEST_FINDRISK_ID="id";
    public static final String RESPUESTAS_TEST_FINDRISK_TEST_FINDRISK="rtf_tfi_id";
    public static final String RESPUESTAS_TEST_FINDRISK_RESPUESTA="rtf_rep_id";
    public static final String RESPUESTAS_TEST_FINDRISK_DESCRIPCION="rtf_descri";
    public static final String RESPUESTAS_TEST_FINDRISK_USUARIO="rtf_usu_id";
    public static final String RESPUESTAS_TEST_FINDRISK_CREATED_AT="created_at";
    public static final String RESPUESTAS_TEST_FINDRISK_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_RESPUESTAS_TEST_FINDRISK="CREATE TABLE "+TABLA_RESPUESTAS_TEST_FINDRISK+" ("+RESPUESTAS_TEST_FINDRISK_ID+" TEXT PRIMARY KEY,"+
            RESPUESTAS_TEST_FINDRISK_TEST_FINDRISK+" TEXT,"+
            RESPUESTAS_TEST_FINDRISK_RESPUESTA+" TEXT,"+
            RESPUESTAS_TEST_FINDRISK_DESCRIPCION+" TEXT,"+
            RESPUESTAS_TEST_FINDRISK_USUARIO+" TEXT,"+
            RESPUESTAS_TEST_FINDRISK_CREATED_AT+" TEXT,"+
            RESPUESTAS_TEST_FINDRISK_UPDATED_AT+" TEXT)";

    public static final String TABLA_SERVICIOS="servicios";
    public static final String SERVICIOS_ID="id";
    public static final String SERVICIOS_DESCRIPCION="serv_desc";
    public static final String SERVICIOS_USUARIO="serv_usu_id";
    public static final String SERVICIOS_CREATED_AT="created_at";
    public static final String SERVICIOS_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_SERVICIOS="CREATE TABLE "+TABLA_SERVICIOS+" ("+SERVICIOS_ID+" TEXT PRIMARY KEY,"+
            SERVICIOS_DESCRIPCION+" TEXT,"+
            SERVICIOS_USUARIO+" TEXT,"+
            SERVICIOS_CREATED_AT+" TEXT,"+
            SERVICIOS_UPDATED_AT+" TEXT)";

    public static final String TABLA_SERVICIOS_ENTIDAD="servicios_entidad";
    public static final String SERVICIOS_ENTIDAD_ID="id";
    public static final String SERVICIOS_ENTIDAD_ENTIDAD="sret_ent_id";
    public static final String SERVICIOS_ENTIDAD_SERVICIO="sret_serv_id";
    public static final String SERVICIOS_ENTIDAD_USUARIO="sret_usu_id";
    public static final String SERVICIOS_ENTIDAD_CREATED_AT="created_at";
    public static final String SERVICIOS_ENTIDAD_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_SERVICIOS_ENTIDAD="CREATE TABLE "+TABLA_SERVICIOS_ENTIDAD+" ("+SERVICIOS_ENTIDAD_ID+" TEXT PRIMARY KEY,"+
            SERVICIOS_ENTIDAD_ENTIDAD+" TEXT,"+
            SERVICIOS_ENTIDAD_SERVICIO+" TEXT,"+
            SERVICIOS_ENTIDAD_USUARIO+" TEXT,"+
            SERVICIOS_ENTIDAD_CREATED_AT+" TEXT,"+
            SERVICIOS_ENTIDAD_UPDATED_AT+" TEXT)";

    public static final String TABLA_TEST_FINDRIKS="test_findrisk";
    public static final String TEST_FINDRISK_ID="id";
    public static final String TEST_FINDRISK_PERSONA="tfi_per_id";
    public static final String TEST_FINDRISK_USUARIO="tfi_usu_id";
    public static final String TEST_FINDRISK_CREATED_AT="created_at";
    public static final String TEST_FINDRISK_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_TEST_FINDRISK="CREATE TABLE "+TABLA_TEST_FINDRIKS+" ("+TEST_FINDRISK_ID+" TEXT PRIMARY KEY,"+
            TEST_FINDRISK_PERSONA+" TEXT,"+
            TEST_FINDRISK_USUARIO+" TEXT,"+
            TEST_FINDRISK_CREATED_AT+" TEXT,"+
            TEST_FINDRISK_UPDATED_AT+" TEXT)";


    //Tabla tipo documento
    public static final String TABLA_TIPO_DOC="tipo_documento";
    public static final String TIPO_DOC_ID="id";
    public static final String TIPO_DOC_CODIGO="tpd_codigo";
    public static final String TIPO_DOC_DESCRIPCION="tpd_descrp";
    public static final String TIPO_DOC_LONGITUD="tpd_longitud";
    public static final String CREAR_TABLA_TIPO_DOC="CREATE TABLE "+TABLA_TIPO_DOC+" ("+TIPO_DOC_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            TIPO_DOC_CODIGO+" TEXT,"+
            TIPO_DOC_DESCRIPCION+" TEXT,"+
            TIPO_DOC_LONGITUD+" TEXT)";


    //Tabla usuarios
    public static final String TABLA_USUARIO="usuarios";
    public static final String USUARIO_ID="id";
    public static final String USUARIO_NOMBRE="usu_nombre";
    public static final String USUARIO_APELLIDO="usu_apelli";
    public static final String USUARIO_IDENTIFICACION="usu_identi";
    public static final String USUARIO_TELEFONO="usu_telefo";
    public static final String USUARIO_EMAIL="usu_email";
    public static final String USUARIO_ROL="usu_rol";
    public static final String USUARIO_FIRMA="usu_firma";
    public static final String USUARIO_FOTO="usu_foto";
    public static final String USUARIO_PASSWORD="password";
    public static final String USUARIO_REMEMBER_TOKEN="remember_token";
    public static final String USUARIO_ESTADO="usu_estado";
    public static final String USUARIO_CREATED_AT="created_at";
    public static final String USUARIO_UPDATED_AT="updated_at";
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("+USUARIO_ID+" TEXT PRIMARY KEY,"+
            USUARIO_NOMBRE+" TEXT,"+
            USUARIO_APELLIDO+" TEXT,"+
            USUARIO_IDENTIFICACION+" TEXT,"+
            USUARIO_TELEFONO+" TEXT,"+
            USUARIO_EMAIL+" TEXT,"+
            USUARIO_ROL+" TEXT,"+
            USUARIO_FIRMA+" TEXT,"+
            USUARIO_FOTO+" TEXT,"+
            USUARIO_PASSWORD+" TEXT,"+
            USUARIO_REMEMBER_TOKEN+" TEXT,"+
            USUARIO_ESTADO+" TEXT,"+
            USUARIO_CREATED_AT+" TEXT,"+
            USUARIO_UPDATED_AT+" TEXT)";

    public static String calcularEdad(String fecha){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date hoy=new Date();

            Date fechaInicial=dateFormat.parse(fecha);
            Date fechaFinal= dateFormat.parse(dateFormat.format(hoy));
            int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
            int anios = dias/365;
            if (anios!=0)return anios+" Años";
            int diasSobran=dias-(anios*365);
            int meses=(int) (diasSobran/30.41666);
            if (meses!=0)return meses+" Meses";
            int diasDiasSobran=(int) (meses*30.4166);
            return dias+" Dias";
            //return "Años:"+anios+" meses:"+meses+" dias:"+diasDiasSobran;
        } catch (ParseException e) {
            e.printStackTrace();
            return "No se pudo calcular edad";
        }
    }
    public static String generarId(String tabla){
        char[] nombre="JONATAN".toCharArray();
        String documento="1069757882";
        Calendar hoy=Calendar.getInstance();
        int aNumber = (int) (100000 * Math.random()) + 1;
        return tabla+nombre[0]+nombre[1]+documento+aNumber+hoy.getTimeInMillis();
    }
    public static String createdAt(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
