--- drop function
DROP TABLE IF EXISTS 
  katalogos,
  paraggelia,
  receipt,
  trapezi,
  servitoros,
  log_file
  CASCADE;
drop function if exists createdb();
drop function if exists dropdb();
drop function if exists insertdb();

CREATE FUNCTION dropdb()
returns void AS 
$$

DROP TABLE IF EXISTS 
  katalogos,
  paraggelia,
  receipt,
  trapezi,
  servitoros,
  log_file
  CASCADE;

$$ LANGUAGE SQL;


--- create function

CREATE FUNCTION createdb()
returns void as 
$$

CREATE TABLE katalogos(
  kid SERIAL,
  konoma VARCHAR(100),
  price float constraint c_price check (price>0),
  availability INT constraint c_availability check (availability=0 or availability>0),
  category VARCHAR(50),
  constraint c_kid PRIMARY KEY(kid)
);

 CREATE TABLE servitoros (
  sid serial,
  onoma VARCHAR(50),
  constraint c_sid PRIMARY KEY(sid)
);                                                                
                                                                 
CREATE TABLE receipt (
  rid serial,
  dateTime TIMESTAMP,
  servitorosID INT not null constraint f_key3 references servitoros(sid),
  constraint c_rid primary key(rid)
);                                                                
                                                                 
                                                                 
CREATE TABLE paraggelia(
  pid serial,
  katalogosID integer not null constraint f_key1 references katalogos(kid) on delete cascade ,
  amount INT,
  receiptID INT not null constraint f_key2 references receipt(rid),
  sxolio varchar(100),
  constraint c_pid primary key(pid)	
);
                                                                 
                                                                 
CREATE TABLE trapezi (
  tid serial,
  receiptID INT constraint f_key references receipt(rid),
  constraint c_tid primary key(tid)	
  );


create table log_file(
table_name text not null,
operation char(1) NOT NULL,
stamp timestamp NOT NULL,
userid varchar(20) NOT NULL
);

-- creating triggers
CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON katalogos
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON servitoros
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON receipt
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON paraggelia
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON trapezi
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

create trigger updateamounttrigger
after insert on paraggelia 
for each row 
execute procedure updateamount();
$$ LANGUAGE SQL;

--Καθε φορα που περναμε μια παραγγελια, θα μειωνει τη διαθεσημοτητα του προιόντος
--χρειαζεται trigger ετσι ωστε οταν γινεται insert μιας παραγγελιας να γινεται
--αφαιρεση των τεμαχιων των φαγητων απο τον καταλογο

create or replace function updateamount() returns trigger as $$
begin 
	update katalogos 
	set availability=availability-new.amount
	where kid=new.katalogosid;
	return null;
end;
$$ language plpgsql;
								 
								 
---log file

CREATE OR REPLACE FUNCTION process_log_file()
RETURNS TRIGGER AS $$
declare tname text ;
BEGIN
tname := TG_TABLE_NAME || ' ';
IF (TG_OP = 'DELETE') THEN
INSERT INTO log_file SELECT tname,'D', now(), user;
ELSIF (TG_OP = 'UPDATE') THEN
INSERT INTO log_file SELECT tname,'U', now(), user;
ELSIF (TG_OP = 'INSERT') THEN
INSERT INTO log_file SELECT tname,'I', now(), user;
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;
								 			 
--- insert function

CREATE FUNCTION insertdb()
returns void as 
$$
BEGIN
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('τουρτα', 7.5, 20, 'Επιδόρπια');
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('μουσακας', 6.5, 0, 'Κυρίως πιάτα');
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('σουβλακια', 5.5, 35, 'Κυρίως πιάτα');
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('μπριζολα', 9, 32, 'Κυρίως πιάτα');
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('πατατες', 2.5, 40, 'Ορεκτικά');
INSERT INTO katalogos(konoma, price, availability, category) VALUES ('χωριατικη', 2.5, 40, 'Σαλάτες');
   
INSERT INTO servitoros(onoma) VALUES ('Θανος');
INSERT INTO servitoros(onoma) VALUES ('Σοφια');
INSERT INTO servitoros(onoma) VALUES ('Κωστας');                                                                 
                                                                
INSERT INTO receipt(dateTime, servitorosID) VALUES ('2020-11-09 16:02:17.623919', 1);
INSERT INTO receipt(dateTime, servitorosID) VALUES ('2020-11-09 16:02:17.697942',2);
INSERT INTO receipt(dateTime, servitorosID) VALUES ('2020-11-09 16:02:17.769762',1);
INSERT INTO receipt(dateTime, servitorosID) VALUES ('2020-11-09 16:30:54.850303',2);
INSERT INTO receipt(dateTime, servitorosID) VALUES (NOW(),3);
INSERT INTO receipt(dateTime, servitorosID) VALUES (NOW(),3);                                                              
                                                                 
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (4, 1, 6);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (6, 1, 5);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (3, 2, 4);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (1, 3, 3);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (5, 2, 2);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (4, 3, 1);
INSERT INTO paraggelia(katalogosID, amount, receiptID) VALUES (3, 2, 5);
                                                                 

INSERT INTO trapezi(receiptid) VALUES (5);
INSERT INTO trapezi(receiptid) VALUES (3);
INSERT INTO trapezi(receiptid) VALUES (1);
INSERT INTO trapezi(receiptid) VALUES (6);
INSERT INTO trapezi(receiptid) VALUES (2);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
INSERT INTO trapezi(receiptid) VALUES (null);
END
$$ LANGUAGE plpgsql;

---dynamic queries

select dropdb();
select createdb();
select insertdb();

create or replace function putTrapezi(int)
returns int as $$
  insert into trapezi(receiptid) values ($1) returning tid;
$$ language sql;

create or replace function putParaggelia(int, int, int)
returns void as $$
  insert into paraggelia(katalogosID, amount, receiptID) values ($1, $2, $3);
$$ language sql;

create or replace function putReceipt(int)
returns int as $$
  insert into receipt(dateTime, servitorosID) values (NOW(), $1) returning rid;
$$ language sql;

create or replace function putServitoros(varchar)
returns void as $$
  insert into servitoros(onoma) values ($1);
$$ language sql;

create or replace function putKatalogos(varchar, float, int, varchar)
returns void as $$
  insert into katalogos(konoma, price, availability, category) values ($1, $2, $3, $4);
$$ language sql;

create or replace function updateKatalogos(int, varchar, float, int, varchar)
returns void as $$
  update katalogos set
  konoma=$2,
  price=$3,
  availability=$4,
  category=$5
  where kid=$1;
$$ language sql;

-- Τραπέζια που σερβίρει ο σερβιτορος x

create or replace function getTrapezia(int) returns 
setof int as $$
	select distinct t.tid from trapezi t
	join receipt r on (r.rid=t.receiptID)
	where r.servitorosID=$1;
$$ language sql;


-- Τι χρωστάει το τραπέζι x

create or replace function getLogariasmo(int) returns float as $$
  select SUM(price*amount) FROM receipt r
  JOIN trapezi t ON t.receiptID=r.rid
  join paraggelia p on p.receiptID=r.rid
  JOIN katalogos kat ON kat.kid=p.katalogosid
  WHERE t.tid=$1;
$$ language sql;

-- Ποσα x category εχει παρει το τραπεζι y

 create or replace function getPosothta(bigint,varchar) returns 
 setof bigint as 
 $$
  SELECT COUNT(*) FROM receipt r
  JOIN paraggelia p ON p.receiptid=r.rid
  JOIN trapezi t ON t.receiptID=r.rid
  JOIN katalogos kat ON kat.kid=p.katalogosid
  WHERE t.tid=$1 AND category=$2;
$$ language sql;

  
-- Ποια τραπεζια εχουν παρει την x επιλογη απο τον καταλογο

create or replace function getEpilogh(varchar) returns 
setof int as $$
	SELECT DISTINCT t.tid FROM trapezi t
	JOIN receipt r ON r.rid=t.receiptid
	JOIN paraggelia p ON p.receiptid=r.rid
	JOIN katalogos kat ON kat.kid=p.katalogosid
	WHERE kat.konoma=$1;
$$ language sql;

-- Ποια τραπεζια ειναι ελευθερα

create or replace function getDiathesima() returns 
setof int as $$
	SELECT t.tid FROM trapezi t
	WHERE t.receiptid IS NULL;
 $$ language sql;

 -- Ποιες παραγγελιες εχουν λογαριασμο πανω απο x ευρώ

create or replace function getposo(float) returns
setof int as $$
	select p.pid from paraggelia p
	join  receipt r on p.receiptID=r.rid 
	join katalogos kat ON kat.kid=p.katalogosid
	group by p.pid
	having SUM(kat.price*p.amount)>$1;
$$ language sql;


-- Ολα τα γευματα για το x category

create or replace function getGeumata(varchar) returns 
setof varchar as $$
	select konoma from katalogos k
	where k.category=$1;
$$ language sql;


-- Ποια φαγητα δεν ειναι διαθεσημα

create or replace function getNotDiathesimaFaghta() returns 
setof varchar as $$
	SELECT k.konoma FROM katalogos k
	WHERE k.availability=0;
 $$ language sql;


-- Ταμειο ημερας

DROP TYPE IF EXISTS tameiohmeras CASCADE;

CREATE TYPE tameiohmeras AS (logariasmos float, onoma TEXT);

create or replace function getTameioHmeras() returns setof tameiohmeras as $$
    select sum(k.price*p.amount),s.onoma from receipt r
    join paraggelia p on p.receiptid = r.rid
    join katalogos k on k.kid = p.katalogosid
    join servitoros s on r.servitorosid = s.sid
    where r.datetime >= current_date
    group by s.onoma;
$$ language sql;

-- Ποσες μεριδες εχουν μεινει απο το x φαγητο

create or replace function getMerides(int) returns int as $$
	select k.availability from katalogos k 
	join paraggelia p on p.katalogosid=p.pid
	where k.kid=$1;
$$ language sql;



-- insert more data

select putKatalogos('Χόρτα', 4.70, 20, 'Ορεκτικά');
select putKatalogos('Μανιτάρια πλευρώτους', 5.60, 15, 'Ορεκτικά');
select putKatalogos('Χορτόπιτα', 4.70, 30, 'Ορεκτικά');
select putKatalogos('Γαύρος μαρινάτος', 5.40, 20, 'Ορεκτικά');
select putKatalogos('Φάβα', 4.90, 20, 'Ορεκτικά');
select putKatalogos('Ρεβύθια Κυκλαδίτικα', 5.40, 15, 'Ορεκτικά');
select putKatalogos('Τυρί σαγανάκι', 4.80, 40, 'Ορεκτικά');
select putKatalogos('Χαλούμι', 5.80, 20, 'Ορεκτικά');
select putKatalogos('Τραγανές πατατούλες', 3.50, 60, 'Ορεκτικά');
select putKatalogos('Σκορδαλιά', 4.20, 30, 'Ορεκτικά');
select putKatalogos('Τυροκαυτερή', 3.70, 25, 'Ορεκτικά');
select putKatalogos('Λουκάνικο καπνιστό', 3.80, 30, 'Ορεκτικά');
select putKatalogos('Βραστά λαχανικά', 6.30, 20, 'Ορεκτικά');
select putKatalogos('Μπαγκέτα ψωμί', 0.60, 200, 'Ορεκτικά');

select putKatalogos('Σαλάτα με αποξηραμένα σύκα', 8.60, 50, 'Σαλάτες');
select putKatalogos('«ΔΙΠΑΕ»', 8.00, 50, 'Σαλάτες');
select putKatalogos('Εποχής', 6.80, 50, 'Σαλάτες');
select putKatalogos('Ρόκα', 6.70, 50, 'Σαλάτες');
select putKatalogos('Πράσινη', 6.80, 50, 'Σαλάτες');
select putKatalogos('Σαλάτα του αγρού', 5.80, 50, 'Σαλάτες');
select putKatalogos('Χωριάτικη', 7.70, 50, 'Σαλάτες');

select putKatalogos('Ψαρόσουπα', 6.10, 25, 'Κυρίως πιάτα');
select putKatalogos('Σουπιές με σπανάκι', 8.80, 30, 'Κυρίως πιάτα');
select putKatalogos('Ρεβύθια Κυκλαδίτικα', 5.40, 25, 'Κυρίως πιάτα');
select putKatalogos('Λιγκουίνι με σπανάκι', 8.40, 35, 'Κυρίως πιάτα');
select putKatalogos('Λαχανοντολμάδες', 8.30, 40, 'Κυρίως πιάτα');
select putKatalogos('Κεφτεδάκια', 7.80, 30, 'Κυρίως πιάτα');
select putKatalogos('Μοσχαράκι γάλακτος', 10.40, 20, 'Κυρίως πιάτα');
select putKatalogos('Σνίτσελ κοτόπουλο', 7.80, 30, 'Κυρίως πιάτα');
select putKatalogos('Κοτόπουλο με σπιτικές χυλοπίτες', 9.30, 20, 'Κυρίως πιάτα');

select putKatalogos('Γιαούρτι με μέλι και καρύδια', 3.20, 100, 'Επιδόρπια');
select putKatalogos('Καρυδόπιτα', 3.30, 100, 'Επιδόρπια');
select putKatalogos('Γιαούρτι με γλυκό βύσσινο', 3.20, 100, 'Επιδόρπια');
select putKatalogos('Ραβανί', 1.50, 100, 'Επιδόρπια');
select putKatalogos('Φρούτα εποχής', 1.80, 100, 'Επιδόρπια');

select putKatalogos('Μάμος', 1.50, 500, 'Μπύρες');
select putKatalogos('Βεργίνα', 1.50, 500, 'Μπύρες');
select putKatalogos('Fischer', 2.00, 250, 'Μπύρες');
select putKatalogos('Kaiser', 2.00, 250, 'Μπύρες');
select putKatalogos('Άλφα', 1.50, 500, 'Μπύρες');
select putKatalogos('Fix', 1.50, 500, 'Μπύρες');
select putKatalogos('Μύθος', 1.50, 500, 'Μπύρες');
select putKatalogos('Amstel', 1.50, 500, 'Μπύρες');
select putKatalogos('Heineken', 1.50, 500, 'Μπύρες');

select putKatalogos('Coca-Cola', 1.40, 500, 'Αναψυκτικά');
select putKatalogos('Sprite', 1.40, 500, 'Αναψυκτικά');
select putKatalogos('Fanta', 1.40, 500, 'Αναψυκτικά');
select putKatalogos('Νερό εμφιαλωμένο ΖΑΓΟΡΙ (1l)', 2.00, 500, 'Αναψυκτικά');

select putKatalogos('Κόκκινο Ημίγλυκο', 2.00, 500, 'Κρασιά');
select putKatalogos('Κόκκινο Ερυθρό', 2.00, 500, 'Κρασιά');
select putKatalogos('Λευκό Ερυθρό', 2.00, 500, 'Κρασιά');

--getters 
create or replace function getTrapezi(int)
returns trapezi as $$
select * from trapezi t
where t.tid=$1;
$$ language sql;

create or replace function getParaggelia(int)
returns paraggelia as $$
select * from paraggelia p 
where p.pid=$1;
$$ language sql;

create or replace function getReceipt(int) 
returns receipt as $$
select * from receipt r
where r.rid=$1;
$$ language sql;

create or replace function getServitoros(varchar)
returns int as $$
select s.sid from servitoros s
where s.onoma=$1;
$$ language sql;


create or replace function getKatalogos(varchar) 
returns int as $$
select k.kid from katalogos k
where k.konoma=$1;
$$ language sql; 

create or replace function updateTable(int, int)
returns void as $$
  update trapezi set receiptid=$2 where tid=$1;
$$ language sql;

create or replace function getCategories()
returns setof varchar as $$
  SELECT DISTINCT category FROM katalogos;
$$ language sql;

create or replace function getAllTrapezia()
returns setof trapezi as $$
  SELECT * FROM trapezi;
$$ language sql;

create or replace function getAllGeumata()
returns setof katalogos as $$
  SELECT * FROM katalogos;
$$ language sql;

create or replace function getAllServitorous() returns 
setof servitoros as $$
  select * from servitoros;
$$ language sql;

create or replace function getAllParaggelies(int) returns 
table(katalogos varchar, amount int, price float) as $$
  select k.konoma, p.amount, (k.price*p.amount) from paraggelia p
  join katalogos k on k.kid=p.katalogosid
  where receiptid=$1;
$$ language sql;

create or replace function getServitorosFromReceipt(int)
  returns varchar as $$
  select s.onoma from servitoros s
  join receipt r on r.servitorosid=s.sid
  where r.rid=$1;
$$ language sql;

create or replace function dropTrapezi(int)
returns void as $$
  delete from trapezi where tid=$1;
$$ language sql;

create or replace function getLogariasmoDate(int) returns timestamp as $$
  select datetime FROM receipt r
  WHERE r.rid=$1;
$$ language sql;

create or replace function getAvailability(varchar)
returns int as $$
  select availability from katalogos
  where konoma=$1;
$$ language sql;

CREATE OR REPLACE FUNCTION getLogFile()
returns setof log_File as $$
select * from log_File;
$$ language sql;

create or replace function dropKatalogos(int)
returns void as $$
  delete from katalogos where kid=$1 CASCADE;
$$ language sql;