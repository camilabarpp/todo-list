export interface TaskModel {
  taskId: number;
  taskTitle: string;
  description: string;
  completed: boolean;
  dueDate: Date;
  category: Category;
}

export interface Category {
  categoryId: number;
  categoryName: string;
}
