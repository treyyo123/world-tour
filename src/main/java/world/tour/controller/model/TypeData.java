package world.tour.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import world.tour.entity.Type;

@Data
@NoArgsConstructor
public class TypeData {
	private Long typeId;
	private String typeName;
	
	public TypeData(Type type) {
		this.typeId = type.getTypeId();
		this.typeName = type.getTypeName();
	}
}
