USE taxReports;
CREATE TRIGGER save_history1 AFTER INSERT
ON report_list FOR EACH ROW
INSERT INTO report_history (`code`, content, author_id, inspector_id, status_id, `comment`)
VALUES (NEW.`code`, NEW.content, NEW.author_id, NEW.inspector_id, NEW.status_id, NEW.`comment`);