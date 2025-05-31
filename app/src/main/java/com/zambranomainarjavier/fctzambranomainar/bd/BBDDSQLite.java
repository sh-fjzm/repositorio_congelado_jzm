package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDDSQLite extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "empresas.db";
    private static final int VERSION_BD = 1;

    public BBDDSQLite(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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

        db.execSQL("CREATE TABLE tag (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE oferta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "url TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "descripcion TEXT" +
                ");");

        db.execSQL("CREATE TABLE empresa_tag (" +
                "empresa_id INTEGER NOT NULL, " +
                "tag_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, tag_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE" +
                ");");

        db.execSQL("CREATE TABLE empresa_oferta (" +
                "empresa_id INTEGER NOT NULL, " +
                "oferta_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, oferta_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (oferta_id) REFERENCES oferta(id) ON DELETE CASCADE" +
                ");");

        db.execSQL("CREATE TABLE datos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "empresa TEXT, " +
                "estudio TEXT, " +
                "descripcion TEXT, " +
                "subcategoria TEXT, " +
                "localidad TEXT" +
                ");");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Diseador y programador web','SARL JMD CONSULTING','Formación Profesional Grado Superior','-Tener 2 aos de experiencia en un puesto similar.- Formacin mnima de Grado medio.- Conocimiento de los programas mencionados en la descripcin del puesto.- Incorporacin inmediata.','Sin especificar','Barcelona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Tcnic@ microinformtico con ingls (horario de oficina)','Grupo Sermicro','Formación Profesional Grado Medio','-FP en Informtica o similares.- 1 ao de experiencia en puestos similares.- Nivel alto de ingls hablado.- Incorporacin inmediata.','Sistemas','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Te gusta programar? unete a accenture','Accenture ofertas de primer empleo:','Formación Profesional Grado Superior','Requisitos - Seleccionamos recin titulados en Grados y Msteres en Informtica y Telecomunicaciones de todas las especialidades, ademas del resto de ingenieras y titulados de FP en DAM y DAW.-  Buen nivel de ingls.','Programación','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador en java (h-m)','Contactel Teleservicios','Ingeniería Técnica','Se requiere:Titulacin de Ingeniera Tcnica o Grado/Diplomatura en carreras universitarias cientfico tcnicas o bien, Ciclo Formativo de Grado Superior en Informtica o similar.Experiencia de 3 aos en implantacin, operativa y desarrollo de aplicaciones e iniciativas de administracin electrnica y web JAVA/SOA.Experiencia de 2 aos en sistemas o herramientas de soporte a la tramitacin y de firma electrnica.Experiencia de 2 aos en implantacin de sedes electrnicas y de procedimientos que se integren en ellas.Experiencia de 1 ao en proyectos de simplificacin y reduccin de carga administrativa.Experiencia de 2 aos en servidores de aplicaciones tomcat 6.x, 7.x.Conocimientos de ITIL, Linux, balanceo y clusterizacin, ENS, gestores de contenidos, ...Habilidades de comunicacin, capacidad analtica y de trabajo en equipo.Se ofrece:Participar en proyectos de alta envergadura.Formacin complementaria a cargo de la empresa.Horario flexible y continuo.','Sistemas','Santa Cruz De Tenerife');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Beca ingenieros informáticos / telecomunicaciones','everis Ofertas de Prácticas','','Slo necesitas estar CURSANDO ACTUALMENTE:- La ingeniera SUPERIOR , TECNICA O GRADO en Informtica/telecomunicacin- Mster relacionado-Buen expediente acadmico-Dinamismo, capacidad de trabajo, iniciativa-Inters para la programacin-Nivel de Ingls medio-alto','Telecomunicaciones','Sevilla');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Tcnico de redes','INELCOM','Formación Profesional Grado Superior','Formacin relacionada con el campo de las redes y telecomunicacionesValorable experiencia similar','Telecomunicaciones','Valencia');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Consultor crm salesforce (lightning)','Indra Producción de Software','Ciclo Formativo Grado Superior','Ingeniera o FP de Grado Superior en Informtica o similarExperiencia profesional de al menos 1 ao en desarrollo y configuracin de CRM de Salesforce.Experiencia en en cualquiera de las nubes Sales, Service o Apps (Force.com)Valoraremos especialmente los conocimientos en componentes Lightning.','ERP, CRM, Business Intelligence','Ciudad Real');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Tecnico back office - vacante temporal','Fnac Sede Central','Grado','Licenciado/grado: ADE, Econmicas,etc..Se valorar:Conocimiento del producto, experiencia en posicin similar. Dominio del Excel nivel avanzado. Ingls: nivel medio. Conocimientos de la cadena de suministro. Capacidad analtica. Proactividad. Trabajo en equipo. Orientacin al cliente.','Compras y aprovisionamiento','Pozuelo De Alarcón');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Operador de sistemas - turno de maana (7 horas)','ICA, Informática y Comunicaciones Avanzadas S.L. (Barcelona)','Ciclo Formativo Grado Superior','Un/a profesional con experiencia de al menos dos aos como Tcnico Informtico y motivacin por la asistencia al usuario. - CFGS en Informtica.- Experiencia en la resolucin de incidencias in situ y en remoto. - Conocimientos de Active Directory y antivirus (Kaspersky).','Sistemas','Barcelona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Qa tester- junior','Sogeti España','Grado','-Estudios de Grado, Ingeniera tcnica o superior en Informtica o Telecomunicaciones.- Nivel de ingls medio-alto.- No se requiere experiencia previa.Se valorarn:- Conocimientos de programacin (Java, C, Python)- Proactividad y ganas de aprender.','Calidad','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Consultor/a sap','Hospiten','Grado','Seleccionamos para nuestra oficina central en Santa Cruz de Tenerife un/a Consultor/a SAP.- Grado en Ingeniera Informtica. Licenciado o Ingeniero Superior Informtica.- Se valorar experiencia en proyectos de consultora. - Disponibilidad de viajar a destinos nacionales e internacionales.- Dominio de ingls: fluido, tanto por escrito como hablado.- Metdico, habilidades de comunicacin y trabajo en equipo.- Se valorarn conocimientos tcnicos en definicin de interfaces (estndares de comunicacin HL7, WSL)','ERP, CRM, Business Intelligence','Santa Cruz De Tenerife');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Administrador/a de sistemas','LUXETALENT','Formación Profesional Grado Superior','-Experiencia previa de al menos ao y medio en funciones similares.-Ciclo Formativo Grado Superior o Ingenieria.-Disponibilidad para hacer jornada completa.-Ingls valorable pero no imprescindible. Portugues valorable pero no imprescindible.-Buenas habilidades comunicativas.-Ser proactivo, con gran capacidad de adaptacin y mostrar iniciativa propia.-Conocimiento lenguajes de programaciones (PHP, python, HTML5, javascript, mysql).-Conocimiento administracin MySql y funcionamiento de redes.-Conocimiento sistemas operativos como MS DOS, Window, Linux, OS X.-Conocimientos de CISCO MERAKI.-Experiencia en soporte remoto.','Sistemas','Barcelona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Tcnico campo microinformtica (horario maana)','ES Field Delivery Spain','Formación Profesional Grado Medio','-Formacin reglada y finalizada mnimo FP de Grado Medio relacionada con sector IT.- Disponer vehculo propio','Hardware, redes y seguridad','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Administrador de redes y comunicaciones','ACCIONA WINDPOWER','Grado','Grado en Informtica, Comunicaciones y/o similares.Nivel de Ingls medio/alto','Administración','Pamplona/Iruña');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Tcnico/a de red junior media jornada-tarde','Zelenza Sistemas de Información','Formación Profesional Grado Superior','Titulados en CFGS Telecomunicaciones, ASIR o similaresInters y vocacin por las redes y las telecomunicaciones.Disponibilidad de incorporacin inmediata.','Telecomunicaciones','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador/analista back end php','Importante empresa sector formacion','Formación Profesional Grado Superior','Requisitos fundamentales:- Estudios mnimos: Ciclo Formativo Grado Superior- Experiencia mnima: Al menos 3 aosSlidos conocimientos de programacin y bases de datos SQLConocimientos especficos que se valorarn:Programacin PHPAngular JSBoostrapHTML 5.0JavascriptJQueryNode JSPostrgressJavaIONICDesarrollo IOS y AndroidHerramientas de ETLHerramientas de DatawarehouseMetodologas de desarrollo giles: SCRUM.Competencias:Trabajo en equipoCapacidad de InnovacinLiderazgo de equipos','Enseñanza','Barcelona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador','PRODIEL','Formación Profesional Grado Superior','1.Formacin reglada:FP medio o superior de Informtica, Grado tcnico en informtica de sistemas o similar.2.Conocimientos:Imprescindibles:Conocimiento en lenguajes de programacin .net, MVC, javascript (jquery)Sql server a nivel de lectura y escritura de datos mediante queries.Deseables:Conocimiento en lenguajes de programacin php, AngularDesarrollo de plataformas mviles, Cordova/phonegap y JavaServidores Web, Apache,IISSistemas Operativos, Windows server, CentOS, DebianCreacion de Web Services','Sistemas','Sevilla');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista desarrollador asp.net','Prosegur','Formación Profesional Grado Superior','Ciclo formativo grado superior en:- Desarrollo aplicaciones multiplataforma/ Desarrollo aplicaciones informticas/ Desarrollo de aplicaciones web- Experiencia 3 aos en desarrollo de aplicaciones ASP.NET Core- Valorable de manera muy positiva tener experiencia y conocimientos en: Desarrollo en Javascript / HTML / jquery / CSS, VUE / REACTValorable: Conocimientos de BPM (AuraPortal, TIBCO, etc)','Programación','Madrid ( Madrid)');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Mobile developer (ios + hbrido) - cliente final','Importante empresa de selección de personal y headhunting','Formación Profesional Grado Superior','-Grado Superior en Desarrollo de Aplicaciones Web, Ingeniera Tcnica, Grado en informtica o similar. Se valorar otro tipo de formacin relacionada con el puesto.- Experiencia de, al menos, 2 aos desarrollando sobre Apps nativas iOS (Swift, ObjectiveC)- Experiencia o inters por el desarrollo de aplicaciones de movilidad bajo entornos hbridos (NativeScript, Ionic, ReactNative...). Se valorarn candidaturas que no cuenten con experiencia en este rea.Requisitos deseados - Experiencia en la creacin y ejecucin de test unitarios y test de integracin.- Desarrollo de aplicaciones con uso de APIs REST y SOAP.- Control de versiones GIT/Bitbucket e integracin continua con Jenkins. - Habituado a trabajar con Scrum con las herramientas Jira/Confluence.- Desarrollo con HTML5, JavaScript, CSS.','Programación','Alcobendas');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Developer frontend','Selectiva - (División IT)','Ingeniería Técnica','Ingeniera Tcnica,  Superior o Grado en Informtica-2-4 aos de experiencia como Desarrollador Front end-Experiencia trabajando con Javascript, Jquery, API REST, HTML5, CSS3, Bootstrap, JSON-Experiencia y/o conocimientos en UI/UX','Programación','Madrid');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Analista programador java','Indra Producción de Software','Formación Profesional Grado Superior','Mnimo Ciclo Formativo Grado Superior en Informtica o similar.Mnimo 2 aos de experiencia como programador en entorno Java.','Programación','Salamanca');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador plc rslogix5000 (inglaterra)','PROCON SYSTEMS','Ciclo Formativo Grado Superior','Requisitos:- Buen nivel de alemn o ingls.- Programador PLC RockWell RSLogix 5000.- Experiencia en el sector automocin. * Disponibilidad para viajar (Espaa y Europa). Incluso ms de un 60% del tiempo durante las fases de puesta en marcha y asistencia. El proyecto inicial es en Inglaterra. * Carnet de conducir. * Coche propio.','Electrónica y automática industrial','Badalona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Recin graduados en ingeniera informtica / telecomunicaciones','everis Ofertas de empleo Profesionales','Grado','Slo necesitas haber finalizado/ estar terminando:- La ingeniera SUPERIOR , TECNICA O GRADO en Informtica o en telecomunicaciones- Mster relacionado-Buen expediente acadmico-Dinamismo, capacidad de trabajo, iniciativa-Inters para la programacin-Nivel de Ingls medio-alto','Programación','Cádiz');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador java','Arestes','Ciclo Formativo Grado Superior','REQUISITOS MNIMOS- Formacin mnima: Ciclo Formativa de Grado superior.- Experiencia mnima de dos aos con Java, HTML, Javascript Nexus.- Git, Jenkins y NexusRequisitos deseados:- Se valorar conocimientos en Dockers.- Swift- polymer- Android','Programación','Guissona');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programador/a  java','atSistemas','','Titulacin en la rama de Informtica (FP o Universitaria)- Experiencia mnima de 3 aos realizando labores de desarrollo. - Conocimientos en tecnologas JAVA J2SE (altos) y J2EE (bsico)- Experiencia en entornos de Integracin continua.- Conocimientos de SOLID, Testing, buenas prcticas de codificacin y cualquier cuestin de desarrollo que permitan mejorar la calidad de nuestros desarrollos.','Programación','A Coruña');");

        db.execSQL("INSERT INTO `datos` (titulo, empresa, estudio, descripcion, subcategoria, localidad) VALUES ('Programadores junior sin experiencia (lleida)','Indra Producción de Software','Grado','Recin graduados universitarios en: Informtica, Telecomunicaciones, Telemtica, Matemticas, Fsica, Industrial, o  Ciclo Formativo de grado superior en informtica DAM y DAW etc.-Imprescindible aportar conocimientos de programacin por los estudios o cursos o prcticas.- Ganas e inquietud para desarrollar tu carrera profesional en el mundo de la programacin- Disponibilidad a trabajar en Lleida','Programación','Lleida');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS empresa_oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa_tag");
        db.execSQL("DROP TABLE IF EXISTS oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS tag");
        db.execSQL("DROP TABLE IF EXISTS datos");
        onCreate(db);
    }

}
