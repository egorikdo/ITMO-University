CREATE OR REPLACE FUNCTION update_character_memories()
RETURNS TRIGGER AS $$
BEGIN
	IF TG_OP = ‘DELETE’ THEN
UPDATE Character SET memories_count = (SELECT COUNT(*) FROM Memory WHERE
Memory.character_id = OLD.character_id) WHERE Character.character_id = 
OLD.character_id;
ELSIF IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
UPDATE Character SET memories_count = (SELECT COUNT(*) FROM Memory WHERE
Memory.character_id = NEW.character_id) WHERE Character.character_id = 
NEW.character_id;
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;
