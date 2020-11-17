--- drop function

CREATE FUNCTION dropdb()
returns void AS 
$$

DROP TABLE IF EXISTS 
  katalogos,
  paraggelia,
  receipt,
  trapezi,
  servitoros,
  log_file;

$$ LANGUAGE SQL;


--- create function

CREATE FUNCTION creatdb()
returns void as 
$$

CREATE TABLE katalogos(
  kid SERIAL,
  konoma VARCHAR(25),
  price real constraint c_price check (price>0),
  availability INT constraint c_availability check (availability=0 or availability>0),
  category VARCHAR(25) constraint c_category check (category in ('Κυριος','Ορεκτικο','Επιδορπιο','Σαλατα','Αναψυκτικο','Κρασι','Μπυρα')),
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
  katalogosID integer not null constraint f_key1 references katalogos(kid) ,
  amount INT,
  receiptID INT not null constraint f_key2 references receipt(rid),
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
								 
$$ LANGUAGE SQL;

								 
								 
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
$$
LANGUAGE plpgsql;

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

								 
								 
--- insert function

CREATE FUNCTION insertdb()
returns void as 
$$

INSERT INTO katalogos VALUES (1, 'τουρτα', 7.5, 20, 'Επιδορπιο');
INSERT INTO katalogos VALUES (2, 'μουσακας', 6.5, 0, 'Κυριος');
INSERT INTO katalogos VALUES (3, 'σουβλακια', 5.5, 35, 'Κυριος');
INSERT INTO katalogos VALUES (4, 'μπριζολα', 9, 32, 'Κυριος');
INSERT INTO katalogos VALUES (5, 'πατατες', 2.5, 40, 'Ορεκτικο');
INSERT INTO katalogos VALUES (6, 'χωριατικη', 2.5, 40, 'Σαλατα');
   
INSERT INTO servitoros VALUES (1, 'Θανος');
INSERT INTO servitoros VALUES (2, 'Σοφια');
INSERT INTO servitoros VALUES (3, 'Κωστας');                                                                 
                                                                
INSERT INTO receipt VALUES (1,'2020-11-09 16:02:17.623919', 1);
INSERT INTO receipt VALUES (2,'2020-11-09 16:02:17.697942',2);
INSERT INTO receipt VALUES (3,'2020-11-09 16:02:17.769762',1);
INSERT INTO receipt VALUES (4,'2020-11-09 16:30:54.850303',2);
INSERT INTO receipt VALUES (5, NOW(),3);
INSERT INTO receipt VALUES (6,NOW(),3);                                                              
                                                                 
INSERT INTO paraggelia VALUES (1, 4, 1,6);
INSERT INTO paraggelia VALUES (2, 6, 1,5);
INSERT INTO paraggelia VALUES (3, 3, 2,4);
INSERT INTO paraggelia VALUES (4, 1, 3,3);
INSERT INTO paraggelia VALUES (5, 5, 2,2);
INSERT INTO paraggelia VALUES (7, 4, 3,1);
INSERT INTO paraggelia VALUES (6, 3, 2,5);
                                                                 

INSERT INTO trapezi VALUES (1, 5);
INSERT INTO trapezi VALUES (2, 3);
INSERT INTO trapezi VALUES (3, 1);
INSERT INTO trapezi VALUES (4, 6);
INSERT INTO trapezi VALUES (5, 2);
INSERT INTO trapezi VALUES (6,null);

$$ LANGUAGE SQL;



---dynamic queries


-- Τραπέζια που σερβίρει ο σερβιτορος x

create or replace function getTrapezia(int) returns 
setof int as $$
	select distinct t.tid from trapezi t
	join receipt r on (r.rid=t.receiptID)
	where r.servitorosID=$1;
$$ language sql;

select * from getTrapezia(2);


-- Τι χρωστάει το τραπέζι x

create or replace function getLogariasmo(float) returns float as $$
  select SUM(price*amount) FROM receipt r
  JOIN trapezi t ON t.receiptID=r.rid
  join paraggelia p on p.receiptID=r.rid
  JOIN katalogos kat ON kat.kid=p.katalogosid
  WHERE t.tid=$1;
$$ language sql;

select * from getLogariasmo(2);

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

select * from getPosothta(1,'Κυριος');

  
-- Ποια τραπεζια εχουν παρει την x επιλογη απο τον καταλογο

create or replace function getEpilogh(varchar) returns 
setof int as $$
	SELECT DISTINCT t.tid FROM trapezi t
	JOIN receipt r ON r.rid=t.receiptid
	JOIN paraggelia p ON p.receiptid=r.rid
	JOIN katalogos kat ON kat.kid=p.katalogosid
	WHERE kat.konoma=$1;
$$ language sql;

select * from getEpilogh('τουρτα');

-- Ποια τραπεζια ειναι ελευθερα

create or replace function getDiathesima() returns 
setof int as $$
	SELECT t.tid FROM trapezi t
	WHERE t.receiptid IS NULL;
 $$ language sql;

 select * from getDiathesima();


 -- Ποιες παραγγελιες εχουν λογαριασμο πανω απο x ευρώ

create or replace function getposo(float) returns
setof int as $$
	select p.pid from paraggelia p
	join  receipt r on p.receiptID=r.rid 
	join katalogos kat ON kat.kid=p.katalogosid
	group by p.pid
	having SUM(kat.price*p.amount)>$1;
$$ language sql;

select * from getposo(10.4);


-- Ολα τα γευματα για το x category

create or replace function getGeumata(varchar) returns 
setof varchar as $$
	select konoma from katalogos k
	where k.category=$1;
$$ language sql;

select * from getgeumata('Κυριος');


-- Ποια φαγητα δεν ειναι διαθεσημα

create or replace function getNotDiathesimaFaghta() returns 
setof varchar as $$
	SELECT k.konoma FROM katalogos k
	WHERE k.availability=0;
 $$ language sql;

select * from getNotDiathesimaFaghta();


-- Ταμειο ημερας

create or replace function getTameioHmeras() returns float as $$
	select SUM(kat.price*p.amount) as tameiohmeras from paraggelia p 
	join receipt r on p.receiptID=r.rid 
	join katalogos kat ON kat.kid=p.katalogosid;
$$ language sql;

select * from getTameioHmeras();

						    
-- Ποσες μεριδες εχουν μεινει απο το x φαγητο

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

create trigger updateamounttrigger
after insert on paraggelia 
for each row 
execute procedure updateamount();


-- to actual function tou query
create or replace function getMerides(int) returns int as $$
	select k.availability from katalogos k 
	join paraggelia p on p.katalogosid=p.pid
	where k.kid=$1;
$$ language sql;

select * from getMerides(1);						   
