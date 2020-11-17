-- Τραπέζια που σερβίρει ο σερβιτορος x

create or replace function getTrapeziaFromServitoros(int) returns 
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
	select SUM(kat.price*p.amount) as logariasmos from paraggelia p 
	join receipt r on p.receiptID=r.rid 
	join katalogos kat ON kat.kid=p.katalogosid;
$$ language sql;

select * from getTameioHmeras();
	

-- Ποσες μεριδες εχουν μεινει απο το x φαγητο
fml
