package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Clase que gestiona la base de datos SQLite de la aplicacion
public class BBDDSQLite extends SQLiteOpenHelper {
    // Nombre del archivo de base de datos
    private static final String NOMBRE_BD = "empresas.db";
    /*
        Necesario para que Android sepa si necesita actualizar la estructura
        de la base de datos
     */
    private static final int VERSION_BD = 1;
    /*
        Constructor de la clase BBDDSQLite que emplea la clase SQLiteOpenHelper,
        encargada de gestionar la base de datos SQLite.
     */
    public BBDDSQLite(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creacion de la tabla 'empresa'
        db.execSQL("CREATE TABLE empresa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "sector TEXT, " +
                "empresa_logo TEXT, " +
                "direccion TEXT, " +
                "ciudad TEXT, " +
                "telefono TEXT, " +
                "email TEXT, " +
                "linkedin_url TEXT, " +
                "web TEXT, " +
                "datos TEXT" +
                ");");
        // Creacion de la tabla 'tag'
        db.execSQL("CREATE TABLE tag (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL" +
                ");");
        // Creacion de la tabla 'oferta'
        db.execSQL("CREATE TABLE oferta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "url TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "descripcion TEXT" +
                ");");
        // Tabla intermedia entre 'empresa' y 'tag' para relaciones N:N
        db.execSQL("CREATE TABLE empresa_tag (" +
                "empresa_id INTEGER NOT NULL, " +
                "tag_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, tag_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE" +
                ");");
        // Tabla intermedia entre 'empresa' y 'oferta' para relaciones N:N
        db.execSQL("CREATE TABLE empresa_oferta (" +
                "empresa_id INTEGER NOT NULL, " +
                "oferta_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, oferta_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (oferta_id) REFERENCES oferta(id) ON DELETE CASCADE" +
                ");");
        // Creacion de la tabla 'datos' utilizada para hacer pruebas del script python de procesamiento de datos
        db.execSQL("CREATE TABLE datos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "empresa TEXT, " +
                "estudio TEXT, " +
                "descripcion TEXT, " +
                "subcategoria TEXT, " +
                "localidad TEXT" +
                ");");
        // Esto son datos de prueba utilizados para hacer el testeo de procesamiento de datos
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Diseador y programador web','SARL JMD CONSULTING','Formación Profesional Grado Superior','-Tener 2 aos de experiencia en un puesto similar.- Formacin mnima de Grado medio.- Conocimiento de los programas mencionados en la descripcin del puesto.- Incorporacin inmediata.','Sin especificar','Barcelona');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador en java (h-m)','Contactel Teleservicios','Ingeniería Técnica','Se requiere:Titulacin de Ingeniera Tcnica o Grado/Diplomatura en carreras universitarias cientfico tcnicas o bien, Ciclo Formativo de Grado Superior en Informtica o similar.Experiencia de 3 aos en implantacin, operativa y desarrollo de aplicaciones e iniciativas de administracin electrnica y web JAVA/SOA.Experiencia de 2 aos en sistemas o herramientas de soporte a la tramitacin y de firma electrnica.Experiencia de 2 aos en implantacin de sedes electrnicas y de procedimientos que se integren en ellas.Experiencia de 1 ao en proyectos de simplificacin y reduccin de carga administrativa.Experiencia de 2 aos en servidores de aplicaciones tomcat 6.x, 7.x.Conocimientos de ITIL, Linux, balanceo y clusterizacin, ENS, gestores de contenidos, ...Habilidades de comunicacin, capacidad analtica y de trabajo en equipo.Se ofrece:Participar en proyectos de alta envergadura.Formacin complementaria a cargo de la empresa.Horario flexible y continuo.','Sistemas','Santa Cruz De Tenerife');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador/analista back end php','Importante empresa sector formacion','Formación Profesional Grado Superior','Requisitos fundamentales:- Estudios mnimos: Ciclo Formativo Grado Superior- Experiencia mnima: Al menos 3 aosSlidos conocimientos de programacin y bases de datos SQLConocimientos especficos que se valorarn:Programacin PHPAngular JSBoostrapHTML 5.0JavascriptJQueryNode JSPostrgressJavaIONICDesarrollo IOS y AndroidHerramientas de ETLHerramientas de DatawarehouseMetodologas de desarrollo giles: SCRUM.Competencias:Trabajo en equipoCapacidad de InnovacinLiderazgo de equipos','Enseñanza','Barcelona');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador','PRODIEL','Formación Profesional Grado Superior','1.Formacin reglada:FP medio o superior de Informtica, Grado tcnico en informtica de sistemas o similar.2.Conocimientos:Imprescindibles:Conocimiento en lenguajes de programacin .net, MVC, javascript (jquery)Sql server a nivel de lectura y escritura de datos mediante queries.Deseables:Conocimiento en lenguajes de programacin php, AngularDesarrollo de plataformas mviles, Cordova/phonegap y JavaServidores Web, Apache,IISSistemas Operativos, Windows server, CentOS, DebianCreacion de Web Services','Sistemas','Sevilla');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista desarrollador asp.net','Prosegur','Formación Profesional Grado Superior','Ciclo formativo grado superior en:- Desarrollo aplicaciones multiplataforma/ Desarrollo aplicaciones informticas/ Desarrollo de aplicaciones web- Experiencia 3 aos en desarrollo de aplicaciones ASP.NET Core- Valorable de manera muy positiva tener experiencia y conocimientos en: Desarrollo en Javascript / HTML / jquery / CSS, VUE / REACTValorable: Conocimientos de BPM (AuraPortal, TIBCO, etc)','Programación','Madrid ( Madrid)');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Mobile developer (ios + hbrido) - cliente final','Importante empresa de selección de personal y headhunting','Formación Profesional Grado Superior','-Grado Superior en Desarrollo de Aplicaciones Web, Ingeniera Tcnica, Grado en informtica o similar. Se valorar otro tipo de formacin relacionada con el puesto.- Experiencia de, al menos, 2 aos desarrollando sobre Apps nativas iOS (Swift, ObjectiveC)- Experiencia o inters por el desarrollo de aplicaciones de movilidad bajo entornos hbridos (NativeScript, Ionic, ReactNative...). Se valorarn candidaturas que no cuenten con experiencia en este rea.Requisitos deseados - Experiencia en la creacin y ejecucin de test unitarios y test de integracin.- Desarrollo de aplicaciones con uso de APIs REST y SOAP.- Control de versiones GIT/Bitbucket e integracin continua con Jenkins. - Habituado a trabajar con Scrum con las herramientas Jira/Confluence.- Desarrollo con HTML5, JavaScript, CSS.','Programación','Alcobendas');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Developer frontend','Selectiva - (División IT)','Ingeniería Técnica','Ingeniera Tcnica,  Superior o Grado en Informtica-2-4 aos de experiencia como Desarrollador Front end-Experiencia trabajando con Javascript, Jquery, API REST, HTML5, CSS3, Bootstrap, JSON-Experiencia y/o conocimientos en UI/UX','Programación','Madrid');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador java','Indra Producción de Software','Formación Profesional Grado Superior','Mnimo Ciclo Formativo Grado Superior en Informtica o similar.Mnimo 2 aos de experiencia como programador en entorno Java.','Programación','Salamanca');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador plc rslogix5000 (inglaterra)','PROCON SYSTEMS','Ciclo Formativo Grado Superior','Requisitos:- Buen nivel de alemn o ingls.- Programador PLC RockWell RSLogix 5000.- Experiencia en el sector automocin. * Disponibilidad para viajar (Espaa y Europa). Incluso ms de un 60% del tiempo durante las fases de puesta en marcha y asistencia. El proyecto inicial es en Inglaterra. * Carnet de conducir. * Coche propio.','Electrónica y automática industrial','Badalona');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador java','Arestes','Ciclo Formativo Grado Superior','REQUISITOS MNIMOS- Formacin mnima: Ciclo Formativa de Grado superior.- Experiencia mnima de dos aos con Java, HTML, Javascript Nexus.- Git, Jenkins y NexusRequisitos deseados:- Se valorar conocimientos en Dockers.- Swift- polymer- Android','Programación','Guissona');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador/a  java','atSistemas','','Titulacin en la rama de Informtica (FP o Universitaria)- Experiencia mnima de 3 aos realizando labores de desarrollo. - Conocimientos en tecnologas JAVA J2SE (altos) y J2EE (bsico)- Experiencia en entornos de Integracin continua.- Conocimientos de SOLID, Testing, buenas prcticas de codificacin y cualquier cuestin de desarrollo que permitan mejorar la calidad de nuestros desarrollos.','Programación','A Coruña');");
        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programadores junior sin experiencia (lleida)','Indra Producción de Software','Grado','Recin graduados universitarios en: Informtica, Telecomunicaciones, Telemtica, Matemticas, Fsica, Industrial, o  Ciclo Formativo de grado superior en informtica DAM y DAW etc.-Imprescindible aportar conocimientos de programacin por los estudios o cursos o prcticas.- Ganas e inquietud para desarrollar tu carrera profesional en el mundo de la programacin- Disponibilidad a trabajar en Lleida','Programación','Lleida');");
    }
    // Elimina las tablas existentes si hay una actualizacion de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS empresa_oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa_tag");
        db.execSQL("DROP TABLE IF EXISTS oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS tag");
        db.execSQL("DROP TABLE IF EXISTS datos");
        // Vuelve a crear las tablas
        onCreate(db);
    }

}
