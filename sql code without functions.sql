
CREATE TABLE katalogos(
  kid INTEGER NOT NULL CONSTRAINT C_KID PRIMARY KEY,
  konoma VARCHAR(25),
  price real constraint c_price check (price>0),
  availability INT constraint c_availability check (availability=0 or availability>0),
  category VARCHAR(25) constraint c_category check (category in ('Κυριος','Ορεκτικο','Επιδορπιο','Σαλατα','Αναψυκτικο','Κρασι','Μπυρα')));

 CREATE TABLE servitoros (
  sid INT not null constraint c_sid PRIMARY KEY,
  onoma VARCHAR(50)
);                                                                
                                                                 
CREATE TABLE receipt (
  rid integer not null constraint c_rid primary key,
  dateTime TIMESTAMP,
  servitorosID INT not null constraint f_key3 references servitoros(sid) 
);                                                                
                                                                 
                                                                 
CREATE TABLE paraggelia(
  pid integer not null constraint c_pid primary key ,
  katalogosID integer not null constraint f_key1 references katalogos(kid) ,
  amount INT,
  receiptID INT not null constraint f_key2 references receipt(rid)
);
                                                                 
                                                                 
CREATE TABLE trapezi (
  tid INT not null constraint c_tid PRIMARY KEY,
  receiptID INT not null constraint f_key references receipt(rid),
  available boolean
);


INSERT INTO katalogos VALUES (1, 'τουρτα', 7.5, 20, 'Επιδορπιο');
INSERT INTO katalogos VALUES (2, 'μουσακας', 6.9000001, 15, 'Κυριος');
INSERT INTO katalogos VALUES (3, 'σουβλακια', 6.19999981, 35, 'Κυριος');
INSERT INTO katalogos VALUES (4, 'μπριζολα', 9, 32, 'Κυριος');
INSERT INTO katalogos VALUES (5, 'πατατες', 2.5, 40, 'Ορεκτικο');
INSERT INTO katalogos VALUES (6, 'χωριατικη', 2.5, 40, 'Σαλατα');
   
INSERT INTO servitoros VALUES (1, 'Θανος');
INSERT INTO servitoros VALUES (2, 'Σοφια');
INSERT INTO servitoros VALUES (3, 'Κωστας');                                                                 
                                                                

-- Ama thes na valeis kialla receipts ta timestamps mporeis na ta vazeis me to NOW()
INSERT INTO receipt VALUES (1,'2020-11-09 16:02:17.623919', 1);
INSERT INTO receipt VALUES (2,'2020-11-09 16:02:17.697942',2);
INSERT INTO receipt VALUES (3,'2020-11-09 16:02:17.769762',1);
INSERT INTO receipt VALUES (4,'2020-11-09 16:30:54.850303',2);
INSERT INTO receipt VALUES (5, NOW(),3);
INSERT INTO receipt VALUES (6,NOW(),3);                                                              
                                                                 
INSERT INTO paraggelia VALUES (1, 2, 1,6);
INSERT INTO paraggelia VALUES (2, 1, 1,5);
INSERT INTO paraggelia VALUES (3, 2, 2,4);
INSERT INTO paraggelia VALUES (4, 1, 3,3);
INSERT INTO paraggelia VALUES (5, 2, 2,2);
INSERT INTO paraggelia VALUES (7, 4, 3,1);
INSERT INTO paraggelia VALUES (6, 3, 2,5);
                                                                 

INSERT INTO trapezi VALUES (1, 5, true);
INSERT INTO trapezi VALUES (2, 3, false);
INSERT INTO trapezi VALUES (3, 1, true);
INSERT INTO trapezi VALUES (4, 6, false);
INSERT INTO trapezi VALUES (5, 2, true);
INSERT INTO trapezi VALUES (6, 4, false);